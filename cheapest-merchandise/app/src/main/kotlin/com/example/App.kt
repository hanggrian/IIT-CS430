package com.example

import javafx.application.Application
import javafx.scene.control.ToggleGroup
import javafx.scene.input.KeyCode.DIGIT1
import javafx.scene.input.KeyCode.DIGIT2
import javafx.scene.input.KeyCode.M
import javafx.scene.input.KeyCode.Q
import javafx.scene.input.KeyCombination.SHORTCUT_DOWN
import javafx.scene.text.Font
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
import ktfx.propertyOf
import ktfx.runLater
import ktfx.windows.minSize
import org.controlsfx.tools.Platform
import org.controlsfx.tools.Platform.OSX

class App : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = launchApplication<App>(*args)
    }

    lateinit var pricesPane: InputColumnPane
    lateinit var promotionsPane: InputColumnPane
    lateinit var outputPane: OutputColumnPane

    val fontProperty = propertyOf(Font.font(12.0))

    override fun start(stage: Stage) {
        stage.title = "Cheapest Merchandise"
        stage.minSize = 600 to 300
        stage.scene {
            vbox {
                menuBar {
                    isUseSystemMenuBar = Platform.getCurrent() == OSX
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
                    "Font" {
                        val group = ToggleGroup()
                        radioMenuItem("Normal") {
                            toggleGroup = group
                            isSelected = true
                            onAction { fontProperty.set(Font.font(12.0)) }
                        }
                        radioMenuItem("Big") {
                            toggleGroup = group
                            onAction { fontProperty.set(Font.font(18.0)) }
                        }
                        radioMenuItem("Huge") {
                            toggleGroup = group
                            onAction { fontProperty.set(Font.font(24.0)) }
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
                    pricesPane = addChild(InputColumnPane(stage, "Price", fontProperty))
                    promotionsPane = addChild(InputColumnPane(stage, "Promotions", fontProperty))
                    outputPane = addChild(OutputColumnPane(stage, fontProperty))
                    outputPane.area.textProperty().bind(
                        stringBindingOf(
                            pricesPane.area.textProperty(),
                            promotionsPane.area.textProperty(),
                            outputPane.choice.selectionModel.selectedItemProperty()
                        ) {
                            try {
                                outputPane.choice.selectionModel.selectedItem
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
