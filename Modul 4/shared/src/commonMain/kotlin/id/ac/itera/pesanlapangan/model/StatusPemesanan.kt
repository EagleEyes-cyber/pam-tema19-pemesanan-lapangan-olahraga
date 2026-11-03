package id.ac.itera.pesanlapangan.model

sealed class StatusPemesanan {
    data object MenungguKonfirmasi : StatusPemesanan()
    data class Dikonfirmasi(val petugas: String) : StatusPemesanan()
    data class Selesai(val catatan: String) : StatusPemesanan()
}
