package id.ac.itera.pesanlapangan

import id.ac.itera.pesanlapangan.data.SumberPemesananPalsu
import id.ac.itera.pesanlapangan.data.daftarPemesananContoh
import id.ac.itera.pesanlapangan.model.FilterStatus
import id.ac.itera.pesanlapangan.model.StatusPemesanan
import id.ac.itera.pesanlapangan.model.formatRupiah
import id.ac.itera.pesanlapangan.model.hitungPerStatus
import id.ac.itera.pesanlapangan.model.saringStatus
import id.ac.itera.pesanlapangan.model.urutkanJadwal
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DaftarPemesananTest {

    @Test
    fun sumberPalsuMemberiMinimalSepuluhData() = runTest {
        val semua = SumberPemesananPalsu().ambilSemua()
        println("Jumlah data contoh: ${semua.size}")
        assertTrue(semua.size >= 10)
    }

    @Test
    fun urutanTerbaruDiAtas() {
        val urut = daftarPemesananContoh().urutkanJadwal(terbaruDulu = true)
        urut.take(3).forEach { println("${it.tanggal} ${it.jamMulai} - ${it.lapangan.nama}") }
        assertEquals(12, urut.first().id)
        assertEquals(1, urut.last().id)
    }

    @Test
    fun filterHanyaMenungguKonfirmasi() {
        val menunggu = daftarPemesananContoh().saringStatus(FilterStatus.Menunggu)
        assertTrue(menunggu.all { it.status is StatusPemesanan.MenungguKonfirmasi })
        println(daftarPemesananContoh().hitungPerStatus())
        assertEquals(menunggu.size, daftarPemesananContoh().hitungPerStatus()[FilterStatus.Menunggu])
    }

    @Test
    fun formatRupiahBenar() {
        assertEquals("Rp100.000", formatRupiah(100000))
        assertEquals("Rp25.000", formatRupiah(25000))
    }
}
