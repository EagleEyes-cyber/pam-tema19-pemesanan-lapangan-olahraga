package id.ac.itera.pesanlapangan.model

enum class FilterStatus(val judul: String) {
    Semua("Semua"),
    Menunggu("Menunggu"),
    Dikonfirmasi("Dikonfirmasi"),
    Selesai("Selesai")
}

fun StatusPemesanan.cocok(filter: FilterStatus): Boolean = when (filter) {
    FilterStatus.Semua -> true
    FilterStatus.Menunggu -> this is StatusPemesanan.MenungguKonfirmasi
    FilterStatus.Dikonfirmasi -> this is StatusPemesanan.Dikonfirmasi
    FilterStatus.Selesai -> this is StatusPemesanan.Selesai
}

fun List<Pemesanan>.saringStatus(filter: FilterStatus): List<Pemesanan> =
    filter { it.status.cocok(filter) }

fun List<Pemesanan>.urutkanJadwal(terbaruDulu: Boolean): List<Pemesanan> =
    if (terbaruDulu) {
        sortedWith(compareByDescending<Pemesanan> { it.tanggal }.thenByDescending { it.jamMulai })
    } else {
        sortedWith(compareBy<Pemesanan> { it.tanggal }.thenBy { it.jamMulai })
    }

fun List<Pemesanan>.hitungPerStatus(): Map<FilterStatus, Int> =
    FilterStatus.entries.associateWith { filter -> count { it.status.cocok(filter) } }

fun formatRupiah(nilai: Int): String =
    "Rp" + nilai.toString().reversed().chunked(3).joinToString(".").reversed()
