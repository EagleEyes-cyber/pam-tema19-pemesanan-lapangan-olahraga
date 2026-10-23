package id.ac.itera.pesanlapangan

import id.ac.itera.pesanlapangan.data.SumberPemesananPalsu
import id.ac.itera.pesanlapangan.data.contohPemesanan
import id.ac.itera.pesanlapangan.data.demoStatus
import id.ac.itera.pesanlapangan.data.muatAman
import id.ac.itera.pesanlapangan.model.StatusPemesanan
import id.ac.itera.pesanlapangan.model.infoCatatan
import id.ac.itera.pesanlapangan.model.label
import id.ac.itera.pesanlapangan.model.ringkas
import id.ac.itera.pesanlapangan.model.warna
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class SumberPemesananPalsuTest {

    private val sumber = SumberPemesananPalsu()

    @Test
    fun modelDataClassCopyDanNullSafety() {
        val a = contohPemesanan(7)
        val b = a.copy(status = StatusPemesanan.Dikonfirmasi("Pak Joko (Admin GOR)"))

        println(a == b)
        println(a.status.label())
        println(b.status.label())
        println(a.catatanTambahan?.length ?: 0)

        assertFalse(a == b)
        assertEquals("Menunggu petugas", a.status.label())
        assertEquals("abu-abu", a.status.warna())
        assertEquals("Ditangani Pak Joko (Admin GOR)", b.status.label())
        assertEquals("kuning", b.status.warna())
        assertEquals(0, a.catatanTambahan?.length ?: 0)
        assertEquals("Belum ada catatan tambahan", a.infoCatatan())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun asyncBersamaanSelesaiSekitar1000ms() = runTest {
        val pemesanan = async { sumber.ambil(7) }
        val riwayat = async { sumber.ambilRiwayat(7) }

        println(pemesanan.await().ringkas())
        println(riwayat.await())
        println("Waktu berjalan: $currentTime ms")
        assertEquals(1000, currentTime)
    }

    @Test
    fun collectMencetakPerubahanStatus() = runTest {
        demoStatus()
    }

    @Test
    fun operatorMenyaringDanMengubahStatus() = runTest {
        val notifikasi = mutableListOf<String>()
        sumber.pantau(7)
            .filter { it.status !is StatusPemesanan.MenungguKonfirmasi }
            .map { it.status.label() }
            .onEach { println("Log: $it") }
            .collect {
                println("Notifikasi: $it")
                notifikasi.add(it)
            }

        assertEquals(
            listOf(
                "Ditangani Pak Joko (Admin GOR)",
                "Selesai: Lapangan selesai digunakan dengan tertib"
            ),
            notifikasi
        )
    }

    @Test
    fun muatAmanMenanganiError() = runTest {
        println(muatAman(7, sumber))
        println(muatAman(0, sumber))
        assertEquals("Gagal memuat pemesanan #0", muatAman(0, sumber))
    }
}
