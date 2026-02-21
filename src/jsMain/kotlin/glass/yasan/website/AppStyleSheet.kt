package glass.yasan.website

import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.maxWidth
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
        width(128.px)
    }

    val bio by style {
        property("text-align", "center")
        property("margin-top", "20px")
        property("margin-bottom", "20px")
    }

    val linkStyle by style {
        display(DisplayStyle.Block)
        property("background-color", "var(--layer-fg)")
        property("color", "var(--text)")
        property("text-align", "center")
        fontSize(1.cssRem)
        fontWeight(600)
        padding(15.px, 0.px)
        margin(12.px, 0.px, 11.px, 0.px)
        property("border", "2px solid var(--layer-bg)")

        self + hover style {
            property("color", "var(--text)")
            property("background-color", "var(--layer-bg)")
        }
    }
}
