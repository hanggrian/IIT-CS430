package com.example

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Stage
import ktfx.bindings.asBoolean
import ktfx.controls.insetsOf
import ktfx.coroutines.onAction
import ktfx.coroutines.onMouseClicked
import ktfx.dialogs.infoAlert
import ktfx.inputs.isDoubleClick
import ktfx.layouts.KtfxGridPane
import ktfx.layouts.button
import ktfx.layouts.label
import ktfx.layouts.textArea
import ktfx.runLater

class OutputColumnPane(stage: Stage) : ColumnPane("Output") {
    init {
        button.text = "Export"
        button.onAction {
            FileChooser().also {
                it.title = "Save output.txt"
                it.extensionFilters += FileChooser.ExtensionFilter("Text Files", "*.txt")
            }.showSaveDialog(stage)?.writeText(area.text)
        }
        button.runLater {
            disableProperty().bind(
                area.textProperty().asBoolean {
                    it?.firstOrNull()?.isDigit() == false
                },
            )
        }
        area.isEditable = false
        area.onMouseClicked {
            if (it.isDoubleClick()) {
                infoAlert("Output box cannot be manually edited.")
            }
        }
    }
}

class InputColumnPane(stage: Stage, title: String) : ColumnPane(title) {
    companion object {
        val TEXT_REGEX = Regex("^[0-9.\\s]*\$")
    }

    init {
        button.text = "Import"
        button.onAction {
            FileChooser().also {
                it.title = "Open ${title.lowercase()}.txt"
                it.extensionFilters += FileChooser.ExtensionFilter("Text Files", "*.txt")
            }.showOpenDialog(stage)?.let {
                area.text = it.readText()
            }
        }
        area.textProperty().addListener { _, old, new ->
            if (!new.matches(TEXT_REGEX)) {
                area.text = old
            }
        }
    }
}

open class ColumnPane(title: String) : KtfxGridPane() {
    companion object {
        val AREA_WIDTH = 170.0
    }

    val label: Label
    val button: Button
    val area: TextArea

    init {
        hgap = 10.0
        vgap = 10.0
        prefWidth = AREA_WIDTH
        minWidth = AREA_WIDTH
        padding = insetsOf(10)
        label = label(title).grid(0, 0).hgrow()
        button = button().grid(0, 1)
        area = textArea { isWrapText = true }.grid(1, 0 to 2).vgrow()
    }
}
