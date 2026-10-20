package id.ac.itera.pesanlapangan

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pesan Lapangan",
    ) {
        App()
    }
}