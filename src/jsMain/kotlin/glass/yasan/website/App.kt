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
                            Text(item.name)
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
    val rel: String? = null,
)

private val links = listOf(
    LinkItem("GitHub", "https://github.com/yasanglass"),
    LinkItem("Play Store", "https://play.google.com/store/apps/dev?id=5035207490031558874"),
    LinkItem("Discord", "https://discord.gg/8BQrfyA"),
    LinkItem("Telegram", "https://t.me/YASANupdates"),
    LinkItem("Gumroad", "https://yasanglass.gumroad.com"),
    LinkItem("Mastodon", "https://mastodon.social/@yasanglass", rel = "me"),
    LinkItem("Bluesky", "https://bsky.app/profile/yasan.glass"),
    LinkItem("Email", "mailto:yasanglass@gmail.com"),
)
