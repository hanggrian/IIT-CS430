package com.example

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.control.ToggleGroup
import javafx.scene.input.KeyCode.DIGIT1
import javafx.scene.input.KeyCode.DIGIT2
import javafx.scene.input.KeyCode.Q
import javafx.scene.input.KeyCombination.SHORTCUT_DOWN
import javafx.stage.Stage
import ktfx.bindings.stringBindingOf
import ktfx.coroutines.onAction
import ktfx.inputs.plus
import ktfx.launchApplication
import ktfx.layouts.menuBar
import ktfx.layouts.menuItem
import ktfx.layouts.radioMenuItem
import ktfx.layouts.scene
import ktfx.layouts.separatorMenuItem
import ktfx.layouts.splitPane
import ktfx.layouts.vbox
import ktfx.windows.minSize
import org.apache.commons.lang3.SystemUtils.IS_OS_MAC_OSX

class App : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = launchApplication<App>(*args)
    }

    lateinit var pricesPane: InputColumnPane
    lateinit var promotionsPane: InputColumnPane
    lateinit var outputPane: OutputColumnPane

    override fun start(stage: Stage) {
        stage.title = "Cheapest Merchandise"
        stage.minSize = 600 to 300
        stage.scene {
            vbox {
                menuBar {
                    isUseSystemMenuBar = IS_OS_MAC_OSX
                    "File" {
                        menuItem("Import price") { onAction { pricesPane.button.fire() } }
                        menuItem("Import promotions") { onAction { promotionsPane.button.fire() } }
                        separatorMenuItem()
                        menuItem("Quit") {
                            accelerator = SHORTCUT_DOWN + Q
                            onAction { Platform.exit() }
                        }
                    }
                    "Edit" {
                        val group = ToggleGroup()
                        radioMenuItem("Greedy parser") {
                            toggleGroup = group
                            isSelected = true
                            accelerator = SHORTCUT_DOWN + DIGIT1
                            onAction { outputPane.parserChoice.selectionModel.select(0) }
                        }
                        radioMenuItem("Knapsack parser") {
                            toggleGroup = group
                            accelerator = SHORTCUT_DOWN + DIGIT2
                            onAction { outputPane.parserChoice.selectionModel.select(1) }
                        }
                    }
                    "Help" {
                        menuItem("About") { onAction { AboutDialog().showAndWait() } }
                    }
                }
                splitPane {
                    pricesPane = addChild(InputColumnPane(stage, "Price"))
                    promotionsPane = addChild(InputColumnPane(stage, "Promotions"))
                    outputPane = addChild(OutputColumnPane(stage))
                    outputPane.area.textProperty().bind(
                        stringBindingOf(
                            pricesPane.area.textProperty(),
                            promotionsPane.area.textProperty(),
                            outputPane.parserChoice.selectionModel.selectedItemProperty()
                        ) {
                            try {
                                outputPane.parserChoice.selectionModel.selectedItem
                                    .parse(pricesPane.area.text, promotionsPane.area.text)
                            } catch (e: Exception) {
                                // e.printStackTrace()
                                e.message
                            }
                        },
                    )
                }.vgrow()
            }
        }
        stage.show()

        pricesPane.area.text = """
            2
            7 3 2
            8 2 5
        """.trimIndent()
        promotionsPane.area.text = """
            2
            1 7 3 5
            2 7 1 8 2 10
        """.trimIndent()
    }
}
