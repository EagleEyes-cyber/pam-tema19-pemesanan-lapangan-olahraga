package id.ac.itera.pesanlapangan.model

data class Pemesanan(
    val id: Int,
    val lapangan: Lapangan,
    val namaPemesan: String,
    val tanggal: String,
    val jamMulai: String,
    val jamSelesai: String,
    val catatanTambahan: String? = null,
    val status: StatusPemesanan = StatusPemesanan.MenungguKonfirmasi
)
