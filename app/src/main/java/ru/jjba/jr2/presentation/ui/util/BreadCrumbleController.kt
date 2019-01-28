package ru.jjba.jr2.presentation.ui.util

data class BreadCrumble(
        val title: String
)

class BreadCrumbleController(
        val crumbles: MutableList<BreadCrumble> = mutableListOf()
) {
    fun getComposedTitle() =
        crumbles.joinToString(separator = " > ") { it.title }

    fun onBackPressed() {
        if (crumbles.size > 0) {
            crumbles.removeAt(crumbles.lastIndex)
        }
    }
}