package glass.yasan.website

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Main
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Picture
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun App() {
    Div(attrs = { classes(AppStyleSheet.container) }) {
        Header {
            Picture {
                Source(attrs = {
                    attr("srcset", "logo_text_light_pure.svg")
                    attr("media", "(prefers-color-scheme: dark)")
                })
                Img(
                    src = "logo_text_dark_pure.svg",
                    attrs = { classes(AppStyleSheet.logoHeader) },
                )
            }
            P(attrs = { classes(AppStyleSheet.bio) }) {
                Text("Developer & Designer")
            }
        }
        Main {
            Ul {
                links.forEach { item ->
                    Li {
                        A(
                            href = item.url,
                            attrs = {
                                target(ATarget.Blank)
                                item.rel?.let { attr("rel", it) }
                                classes(AppStyleSheet.linkStyle)
                            },
                        ) {
                            Span { Text(item.name) }
                            Div(attrs = {
                                classes(AppStyleSheet.linkIcon)
                                style {
                                    property("-webkit-mask-image", "url('${item.icon}')")
                                    property("mask-image", "url('${item.icon}')")
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}

private data class LinkItem(
    val name: String,
    val url: String,
    val icon: String,
    val rel: String? = null,
)

private val links = listOf(
    LinkItem("Email", "mailto:yasanglass@gmail.com", "ic_mail.svg"),
    LinkItem("Discord Server", "https://discord.gg/8BQrfyA", "ic_discord.svg"),
    LinkItem("Telegram Channel", "https://t.me/YASANupdates", "ic_telegram.svg"),
    LinkItem("Play Store", "https://play.google.com/store/apps/dev?id=5035207490031558874", "ic_play_store.svg"),
    LinkItem("GitHub", "https://github.com/yasanglass", "ic_github.svg"),
    LinkItem("Mastodon", "https://mastodon.social/@yasanglass", "ic_mastodon.svg", rel = "me"),
    LinkItem("Crowdin", "https://crowdin.com/profile/yasanglass", "ic_translate.svg"),
)
