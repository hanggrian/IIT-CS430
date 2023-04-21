package com.example

import javafx.scene.control.Dialog
import ktfx.coroutines.onAction
import ktfx.dialogs.buttons
import ktfx.dialogs.headerTitle
import ktfx.layouts.hyperlink
import java.awt.Desktop
import java.net.URL

class AboutDialog : Dialog<Unit>() {
    init {
        headerTitle = "About"
        contentText = "Each merchandise for sale has a ticket price. For example, one silk " +
            "flower for $2 and a vase for $5. But stores always provide some promotions for bulk " +
            "purchase or combinations. For example, 3 silk flowers for $5 or 2 vases+1 silk " +
            "flower for $10. Design and implement a program to find out the minimal payment for " +
            "a purchase."
        dialogPane.expandableContent = hyperlink("View GitHub") {
            onAction {
                Desktop.getDesktop()
                    .browse(URL("https://github.com/hendraanggrian/IIT-CS430/").toURI())
            }
        }
        dialogPane.isExpanded = true
        isResizable = false
        buttons {
            close()
        }
    }
}
