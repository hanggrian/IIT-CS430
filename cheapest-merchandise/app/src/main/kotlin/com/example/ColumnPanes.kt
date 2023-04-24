package com.example

import javafx.geometry.HPos.CENTER
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Stage
import ktfx.bindings.asBoolean
import ktfx.collections.observableListOf
import ktfx.controls.insetsOf
import ktfx.coroutines.onAction
import ktfx.coroutines.onMouseClicked
import ktfx.dialogs.infoAlert
import ktfx.inputs.isDoubleClick
import ktfx.layouts.KtfxGridPane
import ktfx.layouts.button
import ktfx.layouts.choiceBox
import ktfx.layouts.label
import ktfx.layouts.textArea
import ktfx.layouts.tooltip
import ktfx.runLater

class OutputColumnPane(stage: Stage) : ColumnPane("Output") {
    companion object {
        val AREA_WIDTH = 260.0
    }

    val choice: ChoiceBox<Parser>

    init {
        prefWidth = AREA_WIDTH
        minWidth = AREA_WIDTH
        choice = choiceBox(observableListOf(GreedyParser(), DfsParser(), DfsParser(true))) {
            selectionModel.select(0)
        }.grid(0, 1).halign(CENTER)
        button.run {
            columnIndex = 2
            text = "Export"
            onAction {
                FileChooser().also {
                    it.title = "Save output"
                    it.initialFileName = "output.txt"
                    it.extensionFilters += FileChooser.ExtensionFilter("Text files", "*.txt")
                }.showSaveDialog(stage)?.writeText(area.text)
            }
            runLater {
                disableProperty().bind(
                    area.textProperty().asBoolean {
                        it?.firstOrNull()?.isDigit() == false
                    },
                )
            }
        }
        area.run {
            columnSpan = 3
            isEditable = false
            val tip = "Output box cannot be manually edited."
            tooltip(tip)
            onMouseClicked {
                if (it.isDoubleClick()) {
                    infoAlert(tip)
                }
            }
        }
    }
}

class InputColumnPane(stage: Stage, title: String) : ColumnPane(title) {
    companion object {
        val AREA_WIDTH = 150.0
        val TEXT_REGEX = Regex("^[0-9.\\s]*\$")
    }

    init {
        prefWidth = AREA_WIDTH
        minWidth = AREA_WIDTH
        button.run {
            text = "Import"
            onAction {
                FileChooser().also {
                    it.title = "Open ${title.lowercase()}"
                    it.extensionFilters += FileChooser.ExtensionFilter("Text files", "*.txt")
                }.showOpenDialog(stage)?.let {
                    area.text = it.readText()
                }
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
    val label: Label
    val button: Button
    val area: TextArea

    init {
        hgap = 10.0
        vgap = 10.0
        padding = insetsOf(10)
        label = label(title).grid(0, 0).hgrow()
        button = button().grid(0, 1)
        area = textArea { isWrapText = true }.grid(1, 0 to 2).vgrow()
    }
}
