package com.example

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.input.KeyCode.Q
import javafx.scene.input.KeyCombination.SHORTCUT_DOWN
import javafx.stage.Stage
import ktfx.bindings.stringBindingOf
import ktfx.coroutines.onAction
import ktfx.inputs.plus
import ktfx.launchApplication
import ktfx.layouts.menuBar
import ktfx.layouts.menuItem
import ktfx.layouts.scene
import ktfx.layouts.separatorMenuItem
import ktfx.layouts.splitPane
import ktfx.layouts.vbox
import ktfx.windows.minSize
import org.apache.commons.lang3.SystemUtils.IS_OS_MAC_OSX

class CheapestMerchandiseApp : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = launchApplication<CheapestMerchandiseApp>(*args)
    }

    lateinit var pricesPane: ColumnPane
    lateinit var promotionsPane: ColumnPane
    lateinit var outputPane: ColumnPane

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
                        ) {
                            try {
                                BruteForceParser.parse(
                                    pricesPane.area.text,
                                    promotionsPane.area.text,
                                )
                            } catch (e: Exception) {
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
