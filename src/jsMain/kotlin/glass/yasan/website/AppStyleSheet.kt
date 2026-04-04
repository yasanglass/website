package glass.yasan.website

import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.media
import org.jetbrains.compose.web.css.mediaMaxWidth
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width

object AppStyleSheet : StyleSheet() {

    init {
        "body" style {
            property("font-size", "16px")
            property("line-height", "1.5")
            property("background-color", "var(--layer-mid)")
            property("color", "var(--text)")
            margin(35.px, 10.px, 40.px, 10.px)
        }

        "img" style {
            property("margin", "0 auto")
            display(DisplayStyle.Block)
            property("height", "auto")
        }

        "a" style {
            property("text-decoration", "none")
        }

        "ul" style {
            property("list-style", "none")
        }
    }

    val container by style {
        width(100.percent)
        maxWidth(680.px)
        property("margin", "0 auto")
    }

    val logoHeader by style {
        width(200.px)
    }

    val bio by style {
        property("text-align", "center")
        property("margin-top", "20px")
        property("margin-bottom", "20px")
    }

    val linkStyle by style {
        display(DisplayStyle.Flex)
        property("align-items", "center")
        property("justify-content", "space-between")
        property("background-color", "var(--layer-fg)")
        property("color", "var(--text)")
        property("text-align", "start")
        fontSize(1.cssRem)
        fontWeight(700)
        property("text-transform", "uppercase")
        padding(16.px, 20.px)
        margin(8.px, 0.px)
        property("border", "1px solid var(--layer-bg)")
        property("border-radius", "28px")
        property("transition", "background-color 0.2s ease")

        self + hover style {
            property("color", "var(--text)")
            property("background-color", "var(--layer-bg)")
        }
    }

    val privacyHeading by style {
        fontSize(1.75.cssRem)
        property("margin-bottom", "24px")
    }

    val privacySectionHeading by style {
        fontSize(1.25.cssRem)
        property("margin-top", "32px")
        property("margin-bottom", "12px")
    }

    val privacyParagraph by style {
        property("margin-bottom", "16px")
    }

    val privacyList by style {
        property("margin-bottom", "16px")
        property("padding-left", "24px")
        property("list-style", "disc")
    }

    val privacyLink by style {
        property("color", "var(--text)")
        property("text-decoration", "underline")
    }

    val fadeSlideUp by keyframes {
        from {
            property("opacity", "0")
            property("transform", "translateY(20px)")
        }
        to {
            property("opacity", "1")
            property("transform", "translateY(0)")
        }
    }

    val launcherPage by style {
        display(DisplayStyle.Flex)
        property("justify-content", "center")
        property("align-items", "center")
        property("min-height", "calc(100vh - 75px)")
        property("padding", "24px 12px")
        property("box-sizing", "border-box")
    }

    val launcherHero by style {
        display(DisplayStyle.Flex)
        property("flex-direction", "row")
        property("justify-content", "center")
        property("gap", "40px")
        property("width", "fit-content")
        property("max-width", "100%")
        property("flex-wrap", "wrap")
        property("align-items", "stretch")
    }

    val launcherLogo by style {
        display(DisplayStyle.Flex)
        property("align-self", "center")
        property("flex", "0 0 auto")
        property("margin", "0")
        property("padding", "0")
        property("line-height", "0")
        property("animation", "${fadeSlideUp.name} 600ms ease-out both")
    }

    val launcherLogoImage by style {
        property("height", "192px")
        property("width", "auto")
        property("margin", "0")
        property("padding", "0")
        property("display", "block")
    }

    val launcherContent by style {
        display(DisplayStyle.Flex)
        property("flex-direction", "column")
        property("justify-content", "space-between")
        property("gap", "10px")
        property("flex", "0 1 auto")
        property("min-width", "0")
        property("animation", "${fadeSlideUp.name} 600ms ease-out 150ms both")

        media(mediaMaxWidth(600.px)) {
            self style {
                property("align-items", "center")
                property("text-align", "center")
            }
        }
    }

    val launcherTitle by style {
        fontSize(2.75.cssRem)
        fontWeight(700)
        property("line-height", "1.05")
    }

    val launcherSubtitle by style {
        property("margin", "0")
        property("font-size", "1.25rem")
    }

    val launcherButton by style {
        property("width", "fit-content")
        property("gap", "6px")
        property("justify-content", "center")
        property("font-size", "1.05rem")
        property("padding", "18px 24px")
        margin(0.px)
    }

    val linkIcon by style {
        width(24.px)
        property("height", "24px")
        property("margin-left", "12px")
        property("flex-shrink", "0")
        property("background-color", "var(--text)")
        property("-webkit-mask-size", "contain")
        property("mask-size", "contain")
        property("-webkit-mask-repeat", "no-repeat")
        property("mask-repeat", "no-repeat")
    }
}
