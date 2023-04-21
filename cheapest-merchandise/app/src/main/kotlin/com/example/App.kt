package com.example

import javafx.application.Application
import javafx.scene.control.ToggleGroup
import javafx.scene.input.KeyCode.DIGIT1
import javafx.scene.input.KeyCode.DIGIT2
import javafx.scene.input.KeyCode.M
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
import ktfx.runLater
import ktfx.windows.minSize
import org.controlsfx.tools.Platform

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
                    isUseSystemMenuBar = Platform.getCurrent() == Platform.OSX
                    "File" {
                        "Import price" { onAction { pricesPane.button.fire() } }
                        "Import promotions" { onAction { promotionsPane.button.fire() } }
                        "Export output" {
                            runLater { disableProperty().bind(outputPane.button.disableProperty()) }
                            onAction { outputPane.button.fire() }
                        }
                        separatorMenuItem()
                        "Quit" {
                            accelerator = SHORTCUT_DOWN + Q
                            onAction { javafx.application.Platform.exit() }
                        }
                    }
                    "Edit" {
                        Sample.values().forEach { sample ->
                            "Sample from $sample" {
                                onAction {
                                    pricesPane.area.text = sample.prices
                                    promotionsPane.area.text = sample.promotions
                                }
                            }
                        }
                        separatorMenuItem()
                        runLater {
                            val group = ToggleGroup()
                            outputPane.choice.items.forEachIndexed { i, parser ->
                                radioMenuItem("$parser") {
                                    toggleGroup = group
                                    isSelected = i == 0
                                    accelerator = SHORTCUT_DOWN + if (i == 0) DIGIT1 else DIGIT2
                                    onAction { outputPane.choice.selectionModel.select(i) }
                                }
                            }
                        }
                    }
                    "Window" {
                        "Minimize" {
                            accelerator = SHORTCUT_DOWN + M
                            onAction { stage.isIconified = true }
                        }
                        "Zoom" { onAction { stage.isMaximized = true } }
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
                            outputPane.choice.selectionModel.selectedItemProperty()
                        ) {
                            try {
                                outputPane.choice.selectionModel.selectedItem.create()
                                    .parse(pricesPane.area.text, promotionsPane.area.text)
                            } catch (e: Exception) {
                                e.printStackTrace()
                                e.message
                            }
                        },
                    )
                }.vgrow()
            }
        }
        stage.show()

        pricesPane.area.text = Sample.PDF.prices
        promotionsPane.area.text = Sample.PDF.promotions
    }
}
