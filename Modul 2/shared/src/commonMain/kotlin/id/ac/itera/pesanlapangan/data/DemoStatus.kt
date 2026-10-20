package id.ac.itera.pesanlapangan.data

import id.ac.itera.pesanlapangan.model.label

suspend fun demoStatus() {
    val aliran = SumberPemesananPalsu().pantau(1)
    println("Sebelum collect")
    aliran.collect { pemesanan ->
        println(pemesanan.status.label())
    }
    println("Sesudah collect")
}
