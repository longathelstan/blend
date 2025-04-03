package blend.module.impl.other

import blend.module.AbstractModule
import blend.module.ModuleCategory
import java.awt.Color

object TestModule: AbstractModule(
    names = arrayOf("Test"),
    description = "GUI testing only.",
    category = ModuleCategory.OTHER,
) {

    val bool by boolean("Bool", false)
    val list by list("List", arrayOf("Hmm", "idk"))
    val color by color("Color", Color(0, 255, 175))
    val translucent by color("Translucent", Color(0, 255, 175), true)
    val numbers = parent("Numbers")
    val int by int("hi", 3, 0, 10, 1, numbers)
    val double by double("double", 3.0, 3.0, 6.0, 0.1, numbers)

}