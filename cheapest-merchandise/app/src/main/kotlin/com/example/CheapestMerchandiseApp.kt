package com.example

import javafx.application.Application
import javafx.application.Platform
import javafx.geometry.Pos.CENTER
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCombination
import javafx.stage.Stage
import ktfx.bindings.stringBindingOf
import ktfx.controls.insetsOf
import ktfx.coroutines.onAction
import ktfx.inputs.plus
import ktfx.launchApplication
import ktfx.layouts.borderPane
import ktfx.layouts.menuBar
import ktfx.layouts.menuItem
import ktfx.layouts.scene
import ktfx.layouts.separatorMenuItem
import ktfx.layouts.splitPane
import ktfx.layouts.textArea
import ktfx.layouts.vbox
import ktfx.windows.minSize
import org.apache.commons.lang3.SystemUtils.IS_OS_MAC_OSX

class CheapestMerchandiseApp : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = launchApplication<CheapestMerchandiseApp>(*args)
    }

    lateinit var inputBox: InputBox
    lateinit var promotionsBox: InputBox
    lateinit var outputArea: TextArea

    override fun start(stage: Stage) {
        stage.title = "Cheapest Merchandise"
        stage.minSize = 600 to 300
        stage.scene {
            vbox {
                menuBar {
                    isUseSystemMenuBar = IS_OS_MAC_OSX
                    "File" {
                        menuItem("Import price") { onAction { inputBox.button.fire() } }
                        menuItem("Import promotions") { onAction { promotionsBox.button.fire() } }
                        separatorMenuItem()
                        menuItem("Quit") {
                            accelerator = KeyCombination.SHORTCUT_DOWN + KeyCode.Q
                            onAction { Platform.exit() }
                        }
                    }
                    "Help" {
                        menuItem("About") { onAction { AboutDialog().showAndWait() } }
                    }
                }
                splitPane {
                    inputBox = addChild(InputBox(stage, "Price"))
                    promotionsBox = addChild(InputBox(stage, "Promotions"))
                    vbox {
                        prefWidth = InputBox.AREA_WIDTH
                        minWidth = InputBox.AREA_WIDTH
                        padding = insetsOf(10)
                        spacing = 10.0
                        borderPane {
                            left = Label("Output").align(CENTER)
                            right = ktfx.layouts.button("Export") {}
                        }
                        textArea {
                            isEditable = false
                            textProperty().bind(
                                stringBindingOf(
                                    inputBox.area.textProperty(),
                                    promotionsBox.area.textProperty(),
                                ) {
                                    try {
                                        Parser.parse(inputBox.area.text, promotionsBox.area.text)
                                    } catch (e: Exception) {
                                        e.message
                                    }
                                },
                            )
                        }.vgrow()
                    }
                }.vgrow()
            }
        }
        stage.show()

        inputBox.area.text = """
            2
            7 3 2
            8 2 5
        """.trimIndent()
        promotionsBox.area.text = """
            2
            1 7 3 5
            2 7 1 8 2 10
        """.trimIndent()
    }
}
