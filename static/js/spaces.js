const SPACE_IDS = ["morning", "work", "commute", "away", "school", "home", "active", "night"];
const MIDDLE_POOL = ["work", "commute", "away", "school", "home", "active"];
const PREVIEW_SEQUENCE_LENGTH = 720;

const SPACE_LABELS = {
  morning: "Morning",
  work: "Work",
  commute: "Commute",
  away: "Away",
  school: "School",
  home: "Home",
  active: "Active",
  night: "Night",
};

const SPACE_ICON_IDS = {
  morning: "icon-morning",
  work:    "icon-work",
  commute: "icon-commute",
  away:    "icon-away",
  school:  "icon-school",
  home:    "icon-home",
  active:  "icon-active",
  night:   "icon-night",
};

const REVEAL_PER_CELL_DELAY = 0.16;
const REVEAL_PER_CELL_SPAN = 0.5;
const REVEAL_MIN_SCALE = 0.92;
const STAGE_PAUSE_FRACTION = 0.18;
const REVEAL_TRANSLATE_Y_PX = 10;

const SPACE_DURATION_MS = 3200;
const SPACE_START_DELAY_MS = 2550;
const REVEAL_START_DELAY_MS = 2450;
const REVEAL_DURATION_MS = 1200;
const TIMELINE_APPEAR_FADE_FRACTION = 0.2;

const TARGET_CELL_WIDTH_PX = 160;
const MIN_VISIBLE_CELLS = 4;
const MAX_VISIBLE_CELLS = 12;

function mulberry32(seed) {
  let state = seed >>> 0;
  return function() {
    state = (state + 0x6D2B79F5) >>> 0;
    let t = state;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

function shuffle(arr, rng) {
  const a = arr.slice();
  for (let i = a.length - 1; i > 0; i--) {
    const j = Math.floor(rng() * (i + 1));
    [a[i], a[j]] = [a[j], a[i]];
  }
  return a;
}

function buildSpaceSequence() {
  const out = [];
  let dayIndex = 0;
  while (out.length < PREVIEW_SEQUENCE_LENGTH) {
    const rng = mulberry32(dayIndex);
    const middleCount = 2 + Math.floor(rng() * 2);
    const middles = shuffle(MIDDLE_POOL, rng).slice(0, middleCount);
    out.push("morning", ...middles, "night");
    dayIndex++;
  }
  return out.slice(0, PREVIEW_SEQUENCE_LENGTH);
}

const SPACE_SEQUENCE = buildSpaceSequence();

function spaceAt(rawIndex) {
  const wrapped = ((rawIndex % PREVIEW_SEQUENCE_LENGTH) + PREVIEW_SEQUENCE_LENGTH) % PREVIEW_SEQUENCE_LENGTH;
  return SPACE_SEQUENCE[wrapped];
}

function makeCubicBezier(p1x, p1y, p2x, p2y) {
  const NEWTON_ITER = 8;
  const NEWTON_MIN = 1e-3;
  const SUBDIV_PRECISION = 1e-7;
  const SUBDIV_MAX = 12;
  const A = (a1, a2) => 1 - 3 * a2 + 3 * a1;
  const B = (a1, a2) => 3 * a2 - 6 * a1;
  const C = (a1) => 3 * a1;
  const calc = (t, a1, a2) => ((A(a1, a2) * t + B(a1, a2)) * t + C(a1)) * t;
  const slope = (t, a1, a2) => 3 * A(a1, a2) * t * t + 2 * B(a1, a2) * t + C(a1);

  function binarySubdivide(x, a, b) {
    let currentX, currentT, i = 0;
    do {
      currentT = a + (b - a) / 2;
      currentX = calc(currentT, p1x, p2x) - x;
      if (currentX > 0) b = currentT; else a = currentT;
    } while (Math.abs(currentX) > SUBDIV_PRECISION && ++i < SUBDIV_MAX);
    return currentT;
  }

  function newtonRaphson(x, guess) {
    for (let i = 0; i < NEWTON_ITER; i++) {
      const s = slope(guess, p1x, p2x);
      if (s === 0) return guess;
      const cx = calc(guess, p1x, p2x) - x;
      guess -= cx / s;
    }
    return guess;
  }

  return function(x) {
    if (x <= 0) return 0;
    if (x >= 1) return 1;
    let t = x;
    const s = slope(t, p1x, p2x);
    if (s >= NEWTON_MIN) t = newtonRaphson(x, t);
    else if (s !== 0) t = binarySubdivide(x, 0, 1);
    return calc(t, p1y, p2y);
  };
}

const transitionEasing = makeCubicBezier(0.4, 0, 0.6, 1);
const linearOutSlowIn  = makeCubicBezier(0, 0, 0.2, 1);

function stageEasing(t) {
  if (STAGE_PAUSE_FRACTION <= 0) return transitionEasing(t);
  if (t < STAGE_PAUSE_FRACTION) return 0;
  const moving = (t - STAGE_PAUSE_FRACTION) / (1 - STAGE_PAUSE_FRACTION);
  return transitionEasing(moving);
}

function clamp(v, lo, hi) {
  return v < lo ? lo : (v > hi ? hi : v);
}

function initWordmarkBar() {
  const bar = document.querySelector("[data-wordmark-bar]");
  const sentinel = document.querySelector("[data-wordmark-sentinel]");
  if (!bar || !sentinel || !("IntersectionObserver" in window)) return;
  const obs = new IntersectionObserver((entries) => {
    for (const entry of entries) {
      bar.dataset.visible = entry.isIntersecting ? "false" : "true";
      bar.setAttribute("aria-hidden", entry.isIntersecting ? "true" : "false");
    }
  }, { threshold: 0, rootMargin: "-1px 0px 0px 0px" });
  obs.observe(sentinel);
}

function init() {
  initWordmarkBar();

  const timelineEl = document.querySelector("[data-spaces-timeline]");
  if (!timelineEl) return;

  const railEl = document.querySelector("[data-spaces-rail]");
  const reducedMotion = matchMedia("(prefers-reduced-motion: reduce)").matches;

  let VISIBLE = 4;
  let LEFT_BUF = 2;
  let RIGHT_BUF = 3;
  let TOTAL = 6;
  let VIEWPORT_CENTER = 2;
  let cellWidthPx = 160;
  let cells = [];

  function calculateVisibleCount() {
    const viewportPx = timelineEl.clientWidth || window.innerWidth;
    let count = Math.round(viewportPx / TARGET_CELL_WIDTH_PX);
    if (count % 2 !== 0) count -= 1;
    return clamp(count, MIN_VISIBLE_CELLS, MAX_VISIBLE_CELLS);
  }

  function buildCells() {
    railEl.innerHTML = "";
    cells = [];
    for (let i = 0; i < TOTAL; i++) {
      const cell = document.createElement("div");
      cell.className = "spaces-cell";
      cell.innerHTML = `
        <div class="spaces-cell-icon-wrap">
          <svg class="spaces-cell-icon" viewBox="0 0 960 960" aria-hidden="true"><use href=""/></svg>
        </div>
        <span class="spaces-cell-label"></span>
        <div class="spaces-cell-bar">
          <div class="spaces-cell-bar-track"></div>
          <div class="spaces-cell-bar-fill"></div>
          <div class="spaces-cell-bar-tick"></div>
        </div>
      `;
      railEl.appendChild(cell);
      cells.push({
        el: cell,
        use: cell.querySelector("use"),
        svg: cell.querySelector(".spaces-cell-icon"),
        iconWrap: cell.querySelector(".spaces-cell-icon-wrap"),
        label: cell.querySelector(".spaces-cell-label"),
        fill: cell.querySelector(".spaces-cell-bar-fill"),
        spaceId: null,
      });
    }
  }

  function setCellSpace(cell, spaceId) {
    if (cell.spaceId === spaceId) return;
    cell.spaceId = spaceId;
    const iconId = SPACE_ICON_IDS[spaceId];
    const iconEl = document.getElementById(iconId);
    cell.use.setAttribute("href", "#" + iconId);
    cell.svg.setAttribute("viewBox", iconEl ? iconEl.getAttribute("viewBox") : "0 0 960 960");
    cell.label.textContent = SPACE_LABELS[spaceId];
  }

  function applyLayout(activeIndex) {
    VISIBLE = calculateVisibleCount();
    LEFT_BUF = Math.floor(VISIBLE / 2);
    RIGHT_BUF = VISIBLE - LEFT_BUF + 1;
    TOTAL = LEFT_BUF + 1 + RIGHT_BUF;
    VIEWPORT_CENTER = VISIBLE / 2;
    cellWidthPx = (timelineEl.clientWidth || window.innerWidth) / VISIBLE;

    timelineEl.style.setProperty("--cell-px", cellWidthPx.toFixed(3) + "px");
    timelineEl.style.setProperty("--total-cells", String(TOTAL));

    buildCells();
    for (let i = 0; i < TOTAL; i++) {
      const offsetFromActive = i - LEFT_BUF;
      setCellSpace(cells[i], spaceAt(activeIndex + offsetFromActive));
    }
  }

  applyLayout(0);

  let resizeRaf = null;
  let lastActiveIndex = 0;
  window.addEventListener("resize", () => {
    if (resizeRaf !== null) cancelAnimationFrame(resizeRaf);
    resizeRaf = requestAnimationFrame(() => {
      resizeRaf = null;
      applyLayout(lastActiveIndex);
    });
  });

  if (reducedMotion) {
    timelineEl.style.setProperty("--reveal", "1");
    railEl.style.setProperty("--slide-x", "0");
    for (let i = 0; i < TOTAL; i++) {
      const cell = cells[i];
      const offsetFromActive = i - LEFT_BUF;
      cell.iconWrap.style.setProperty("--cell-alpha", "1");
      cell.iconWrap.style.setProperty("--cell-scale", "1");
      cell.iconWrap.style.setProperty("--cell-ty", "0px");
      cell.label.style.setProperty("--cell-alpha", "1");
      cell.label.style.setProperty("--cell-ty", "0px");
      cell.fill.style.setProperty("--fill", "0");
      cell.el.dataset.active = offsetFromActive === 0 ? "true" : "false";
      cell.el.dataset.tickPassed = offsetFromActive <= 0 ? "true" : "false";
    }
    return;
  }

  const startTime = performance.now();

  function renderFrame(now) {
    const elapsed = now - startTime;
    const rawAdvance = Math.max(0, (elapsed - SPACE_START_DELAY_MS) / SPACE_DURATION_MS);
    const activeIndex = Math.floor(rawAdvance);
    const cyclePhase = clamp(rawAdvance - activeIndex, 0, 1);
    const eased = stageEasing(cyclePhase);
    const slideOffset = -eased;

    const revealLinear = clamp((elapsed - REVEAL_START_DELAY_MS) / REVEAL_DURATION_MS, 0, 1);
    const revealProgress = linearOutSlowIn(revealLinear);
    const timelineFade = clamp(revealProgress / TIMELINE_APPEAR_FADE_FRACTION, 0, 1);
    timelineEl.style.setProperty("--reveal", timelineFade.toFixed(4));

    railEl.style.setProperty("--slide-x", slideOffset.toFixed(4));

    if (activeIndex !== lastActiveIndex) {
      for (let i = 0; i < TOTAL; i++) {
        const offsetFromActive = i - LEFT_BUF;
        setCellSpace(cells[i], spaceAt(activeIndex + offsetFromActive));
      }
      lastActiveIndex = activeIndex;
    }

    for (let i = 0; i < TOTAL; i++) {
      const cell = cells[i];
      const offsetFromActive = i - LEFT_BUF;
      const cellLeftViewport = i + slideOffset;
      const splitFraction = clamp(VIEWPORT_CENTER - cellLeftViewport, 0, 1);
      const isLeftTickPassed = cellLeftViewport <= VIEWPORT_CENTER;
      const isActive = offsetFromActive === 0;

      let markerAlpha;
      if (offsetFromActive <= 0) markerAlpha = 1;
      else markerAlpha = clamp(
        (revealProgress - (offsetFromActive - 1) * REVEAL_PER_CELL_DELAY) / REVEAL_PER_CELL_SPAN,
        0, 1
      );
      const markerScale = REVEAL_MIN_SCALE + (1 - REVEAL_MIN_SCALE) * markerAlpha;
      const markerTy = REVEAL_TRANSLATE_Y_PX * (1 - markerAlpha);

      cell.iconWrap.style.setProperty("--cell-alpha", markerAlpha.toFixed(4));
      cell.iconWrap.style.setProperty("--cell-scale", markerScale.toFixed(4));
      cell.iconWrap.style.setProperty("--cell-ty", markerTy.toFixed(2) + "px");
      cell.label.style.setProperty("--cell-alpha", markerAlpha.toFixed(4));
      cell.label.style.setProperty("--cell-ty", markerTy.toFixed(2) + "px");
      cell.fill.style.setProperty("--fill", splitFraction.toFixed(4));
      cell.el.dataset.active = isActive ? "true" : "false";
      cell.el.dataset.tickPassed = isLeftTickPassed ? "true" : "false";
    }

    requestAnimationFrame(renderFrame);
  }

  requestAnimationFrame(renderFrame);
}

if (document.readyState === "loading") {
  document.addEventListener("DOMContentLoaded", init);
} else {
  init();
}
