package id.ac.itera.pesanlapangan.data

import id.ac.itera.pesanlapangan.model.Lapangan
import id.ac.itera.pesanlapangan.model.Pemesanan
import id.ac.itera.pesanlapangan.model.StatusPemesanan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

fun contohPemesanan(id: Int) = Pemesanan(
    id = id,
    lapangan = Lapangan(
        id = 1,
        nama = "Lapangan Futsal A GOR ITERA",
        jenisOlahraga = "Futsal",
        hargaPerJam = 100000
    ),
    namaPemesan = "Muhammad Faza Prasetyo",
    tanggal = "2026-10-20",
    jamMulai = "19.00",
    jamSelesai = "21.00",
    catatanTambahan = null
)

class SumberPemesananPalsu : Sumber<Pemesanan> {

    override suspend fun ambil(id: Int): Pemesanan {
        delay(1000) // pura-pura menunggu server
        return contohPemesanan(id)
    }

    suspend fun ambilRiwayat(id: Int): List<String> {
        delay(800)
        return listOf("Diajukan 18.10", "Dikonfirmasi 18.40")
    }

    suspend fun muatDetail(id: Int): Pemesanan =
        withContext(Dispatchers.IO) {
            ambil(id)
        }

    override fun pantau(id: Int): Flow<Pemesanan> = flow {
        val awal = contohPemesanan(id)
        emit(awal.copy(status = StatusPemesanan.MenungguKonfirmasi))
        delay(1500)
        emit(awal.copy(status = StatusPemesanan.Dikonfirmasi("Pak Joko (Admin GOR)")))
        delay(2000)
        emit(
            awal.copy(
                catatanTambahan = "Pinjam rompi 2 set",
                status = StatusPemesanan.Selesai("Lapangan selesai digunakan dengan tertib")
            )
        )
    }
}
