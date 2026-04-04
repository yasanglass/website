package glass.yasan.website

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Picture
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun YasanLauncher() {
    Div(attrs = { classes(AppStyleSheet.launcherPage) }) {
        Div(attrs = { classes(AppStyleSheet.launcherHero) }) {
            Picture(attrs = { classes(AppStyleSheet.launcherLogo) }) {
                Source(attrs = {
                    attr("srcset", "/yasan-launcher/yasan-launcher-on-dark.svg")
                    attr("media", "(prefers-color-scheme: dark)")
                })
            Img(
                src = "/yasan-launcher/yasan-launcher-on-light.svg",
                attrs = {
                    attr("alt", "Yasan Launcher")
                    classes(AppStyleSheet.launcherLogoImage)
                },
            )
        }
            Div(attrs = { classes(AppStyleSheet.launcherContent) }) {
                Span(attrs = { classes(AppStyleSheet.launcherTitle) }) {
                    Text("Yasan Launcher")
                }
                P(attrs = { classes(AppStyleSheet.launcherSubtitle) }) {
                    Text("AI-Powered Android Launcher")
                }
                A(
                    href = "https://play.google.com/store/apps/details?id=yasan.space.mnml.ai.launcher",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        classes(AppStyleSheet.linkStyle, AppStyleSheet.launcherButton)
                    },
                ) {
                    Span { Text("Get on Google Play") }
                    Div(attrs = {
                        classes(AppStyleSheet.linkIcon)
                        style {
                            property("-webkit-mask-image", "url('/ic_play_store.svg')")
                            property("mask-image", "url('/ic_play_store.svg')")
                        }
                    })
                }
            }
        }
    }
}
