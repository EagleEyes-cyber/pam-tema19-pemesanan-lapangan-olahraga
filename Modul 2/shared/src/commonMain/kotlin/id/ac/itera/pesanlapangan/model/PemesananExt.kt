package id.ac.itera.pesanlapangan.model

fun StatusPemesanan.warna(): String = when (this) {
    StatusPemesanan.MenungguKonfirmasi -> "abu-abu"
    is StatusPemesanan.Dikonfirmasi -> "kuning"
    is StatusPemesanan.Selesai -> "hijau"
}

fun StatusPemesanan.label(): String = when (this) {
    StatusPemesanan.MenungguKonfirmasi -> "Menunggu petugas"
    is StatusPemesanan.Dikonfirmasi -> "Ditangani $petugas"
    is StatusPemesanan.Selesai -> "Selesai: $catatan"
}

fun Pemesanan.infoCatatan(): String =
    catatanTambahan?.takeIf { it.isNotBlank() } ?: "Belum ada catatan tambahan"

fun Pemesanan.ringkas(): String =
    "#$id ${lapangan.nama} (${lapangan.jenisOlahraga}) - $namaPemesan, $tanggal ($jamMulai-$jamSelesai)"
