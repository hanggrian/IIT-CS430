package com.example

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Stage
import ktfx.controls.insetsOf
import ktfx.coroutines.onAction
import ktfx.layouts.KtfxVBox
import ktfx.layouts.borderPane
import ktfx.layouts.textArea

class InputBox(
    stage: Stage,
    title: String,
) : KtfxVBox(10.0) {

    companion object {
        val AREA_WIDTH = 170.0
        val TEXT_REGEX = Regex("^[0-9\\s]*\$")
    }

    val label = Label(title)
    val button = ktfx.layouts.button("Import") {
        onAction {
            FileChooser().also { it.title = "Open $title.txt" }
                .showOpenDialog(stage)
        }
    }
    val area: TextArea

    init {
        prefWidth = AREA_WIDTH
        minWidth = AREA_WIDTH
        padding = insetsOf(10)
        borderPane {
            left = label.align(Pos.CENTER)
            right = button
        }
        area = textArea {
            isWrapText = true
            textProperty().addListener { _, old, new ->
                if (!new.matches(TEXT_REGEX)) {
                    text = old
                }
            }
        }.vgrow()
    }
}
