package id.ac.itera.pesanlapangan.data

import id.ac.itera.pesanlapangan.model.Lapangan
import id.ac.itera.pesanlapangan.model.Pemesanan
import id.ac.itera.pesanlapangan.model.StatusPemesanan

val daftarLapangan = listOf(
    Lapangan(id = 1, nama = "Lapangan Futsal A GOR ITERA", jenisOlahraga = "Futsal", hargaPerJam = 100000),
    Lapangan(id = 2, nama = "Lapangan Futsal B GOR ITERA", jenisOlahraga = "Futsal", hargaPerJam = 90000),
    Lapangan(id = 3, nama = "Lapangan Badminton 1", jenisOlahraga = "Badminton", hargaPerJam = 40000),
    Lapangan(id = 4, nama = "Lapangan Basket Outdoor", jenisOlahraga = "Basket", hargaPerJam = 75000),
    Lapangan(id = 5, nama = "Lapangan Voli Embung", jenisOlahraga = "Voli", hargaPerJam = 50000),
    Lapangan(id = 6, nama = "Lapangan Tenis Meja", jenisOlahraga = "Tenis Meja", hargaPerJam = 25000)
)

private fun lapangan(id: Int): Lapangan = daftarLapangan.first { it.id == id }

fun daftarPemesananContoh(): List<Pemesanan> = listOf(
    Pemesanan(
        id = 1, lapangan = lapangan(1), namaPemesan = "Bimo Auliano",
        tanggal = "2026-10-18", jamMulai = "19.00", jamSelesai = "21.00",
        catatanTambahan = "Sparing antar prodi",
        status = StatusPemesanan.Selesai("Lapangan selesai digunakan dengan tertib")
    ),
    Pemesanan(
        id = 2, lapangan = lapangan(3), namaPemesan = "Jova Pratama Nihanda",
        tanggal = "2026-10-19", jamMulai = "16.00", jamSelesai = "18.00",
        status = StatusPemesanan.Selesai("Shuttlecock dikembalikan")
    ),
    Pemesanan(
        id = 3, lapangan = lapangan(4), namaPemesan = "Muhammad Faza Prasetyo",
        tanggal = "2026-10-20", jamMulai = "15.30", jamSelesai = "17.30",
        catatanTambahan = "Latihan UKM Basket",
        status = StatusPemesanan.Selesai("Bola basket lengkap")
    ),
    Pemesanan(
        id = 4, lapangan = lapangan(5), namaPemesan = "Rina Kartika",
        tanggal = "2026-10-21", jamMulai = "07.00", jamSelesai = "09.00",
        status = StatusPemesanan.Dikonfirmasi("Pak Joko (Admin GOR)")
    ),
    Pemesanan(
        id = 5, lapangan = lapangan(2), namaPemesan = "Dimas Saputra",
        tanggal = "2026-10-22", jamMulai = "20.00", jamSelesai = "22.00",
        catatanTambahan = "Pinjam rompi 2 set",
        status = StatusPemesanan.Dikonfirmasi("Bu Sari (Admin GOR)")
    ),
    Pemesanan(
        id = 6, lapangan = lapangan(6), namaPemesan = "Aulia Rahma",
        tanggal = "2026-10-23", jamMulai = "13.00", jamSelesai = "14.00",
        status = StatusPemesanan.MenungguKonfirmasi
    ),
    Pemesanan(
        id = 7, lapangan = lapangan(1), namaPemesan = "Muhammad Faza Prasetyo",
        tanggal = "2026-10-23", jamMulai = "19.00", jamSelesai = "21.00",
        catatanTambahan = "Turnamen kelas RB",
        status = StatusPemesanan.Dikonfirmasi("Pak Joko (Admin GOR)")
    ),
    Pemesanan(
        id = 8, lapangan = lapangan(3), namaPemesan = "Yoga Pratama",
        tanggal = "2026-10-24", jamMulai = "08.00", jamSelesai = "10.00",
        status = StatusPemesanan.MenungguKonfirmasi
    ),
    Pemesanan(
        id = 9, lapangan = lapangan(4), namaPemesan = "Salsabila Putri",
        tanggal = "2026-10-24", jamMulai = "16.00", jamSelesai = "18.00",
        catatanTambahan = "Butuh papan skor",
        status = StatusPemesanan.MenungguKonfirmasi
    ),
    Pemesanan(
        id = 10, lapangan = lapangan(5), namaPemesan = "Fajar Nugroho",
        tanggal = "2026-10-25", jamMulai = "06.30", jamSelesai = "08.30",
        status = StatusPemesanan.Dikonfirmasi("Bu Sari (Admin GOR)")
    ),
    Pemesanan(
        id = 11, lapangan = lapangan(2), namaPemesan = "Bimo Auliano",
        tanggal = "2026-10-26", jamMulai = "19.00", jamSelesai = "20.00",
        status = StatusPemesanan.MenungguKonfirmasi
    ),
    Pemesanan(
        id = 12, lapangan = lapangan(6), namaPemesan = "Jova Pratama Nihanda",
        tanggal = "2026-10-27", jamMulai = "10.00", jamSelesai = "12.00",
        catatanTambahan = "Latihan sebelum lomba",
        status = StatusPemesanan.MenungguKonfirmasi
    )
)
