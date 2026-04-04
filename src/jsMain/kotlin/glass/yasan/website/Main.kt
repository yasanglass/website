package glass.yasan.website

import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        when (window.location.pathname.trimEnd('/')) {
            "/yasan-launcher" -> {
                document.title = "Yasan Launcher"
                YasanLauncher()
            }
            "/yasan-launcher/privacy" -> {
                document.title = "Privacy Policy - Yasan Launcher"
                YasanLauncherPrivacyPolicy()
            }
            else -> App()
        }
    }
}
