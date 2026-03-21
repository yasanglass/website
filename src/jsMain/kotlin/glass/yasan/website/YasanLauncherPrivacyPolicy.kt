package glass.yasan.website

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun YasanLauncherPrivacyPolicy() {
    Div(attrs = { classes(AppStyleSheet.container) }) {
        H1(attrs = { classes(AppStyleSheet.privacyHeading) }) {
            Text("Privacy Policy of Yasan Launcher")
        }

        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("This privacy policy applies to the Yasan Launcher app (hereby referred to as \"Application\") for mobile devices that was created by Yasan Ghaffarian (hereby referred to as \"Service Provider\") as a Freemium service. This service is intended for use \"AS IS\".")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Information Collection and Use")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Application collects information when you download and use it. This information may include information such as")
        }
        Ul(attrs = { classes(AppStyleSheet.privacyList) }) {
            Li { Text("Your device's Internet Protocol address (e.g. IP address)") }
            Li { Text("The pages of the Application that you visit, the time and date of your visit, the time spent on those pages") }
            Li { Text("The time spent on the Application") }
            Li { Text("The operating system you use on your mobile device") }
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Application does not gather precise information about the location of your mobile device.")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Service Provider may use the information you provided to contact you from time to time to provide you with important information, required notices and marketing promotions.")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("For a better experience, while using the Application, the Service Provider may require you to provide us with certain personally identifiable information. The information that the Service Provider request will be retained by them and used as described in this privacy policy.")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Third Party Access")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("Only aggregated, anonymized data is periodically transmitted to external services to aid the Service Provider in improving the Application and their service. The Service Provider may share your information with third parties in the ways that are described in this privacy statement.")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("Please note that the Application utilizes third-party services that have their own Privacy Policy about handling data. Below are the links to the Privacy Policy of the third-party service providers used by the Application:")
        }
        Ul(attrs = { classes(AppStyleSheet.privacyList) }) {
            Li {
                A(
                    href = "https://policies.google.com/privacy",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        classes(AppStyleSheet.privacyLink)
                    },
                ) { Text("Google Play Services") }
            }
            Li {
                A(
                    href = "https://firebase.google.com/support/privacy",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        classes(AppStyleSheet.privacyLink)
                    },
                ) { Text("Google Analytics for Firebase") }
            }
            Li {
                A(
                    href = "https://firebase.google.com/support/privacy",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        classes(AppStyleSheet.privacyLink)
                    },
                ) { Text("Firebase Crashlytics") }
            }
            Li {
                A(
                    href = "https://www.revenuecat.com/privacy",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        classes(AppStyleSheet.privacyLink)
                    },
                ) { Text("RevenueCat") }
            }
            Li {
                A(
                    href = "https://posthog.com/privacy",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        classes(AppStyleSheet.privacyLink)
                    },
                ) { Text("PostHog") }
            }
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Service Provider may disclose User Provided and Automatically Collected Information:")
        }
        Ul(attrs = { classes(AppStyleSheet.privacyList) }) {
            Li { Text("as required by law, such as to comply with a subpoena, or similar legal process;") }
            Li { Text("when they believe in good faith that disclosure is necessary to protect their rights, protect your safety or the safety of others, investigate fraud, or respond to a government request;") }
            Li { Text("with their trusted services providers who work on their behalf, do not have an independent use of the information we disclose to them, and have agreed to adhere to the rules set forth in this privacy statement.") }
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Opt-Out Rights")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("You can stop all collection of information by the Application easily by uninstalling it. You may use the standard uninstall processes as may be available as part of your mobile device or via the mobile application marketplace or network.")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Data Retention Policy")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Service Provider will retain User Provided data for as long as you use the Application and for a reasonable time thereafter. If you'd like them to delete User Provided Data that you have provided via the Application, please contact them at yasanglass@gmail.com and they will respond in a reasonable time.")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Children")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Service Provider does not use the Application to knowingly solicit data from or market to children under the age of 13.")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Application does not address anyone under the age of 13. The Service Provider does not knowingly collect personally identifiable information from children under 13 years of age. In the case the Service Provider discover that a child under 13 has provided personal information, the Service Provider will immediately delete this from their servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact the Service Provider (yasanglass@gmail.com) so that they will be able to take the necessary actions.")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Security")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("The Service Provider is concerned about safeguarding the confidentiality of your information. The Service Provider provides physical, electronic, and procedural safeguards to protect information the Service Provider processes and maintains.")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Changes")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("This Privacy Policy may be updated from time to time for any reason. The Service Provider will notify you of any changes to the Privacy Policy by updating this page with the new Privacy Policy. You are advised to consult this Privacy Policy regularly for any changes, as continued use is deemed approval of all changes.")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("This privacy policy is effective as of 2025-10-26")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Your Consent")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("By using the Application, you are consenting to the processing of your information as set forth in this Privacy Policy now and as amended by us.")
        }

        H2(attrs = { classes(AppStyleSheet.privacySectionHeading) }) {
            Text("Contact Us")
        }
        P(attrs = { classes(AppStyleSheet.privacyParagraph) }) {
            Text("If you have any questions regarding privacy while using the Application, or have questions about the practices, please contact the Service Provider via email at yasanglass@gmail.com.")
        }
    }
}
