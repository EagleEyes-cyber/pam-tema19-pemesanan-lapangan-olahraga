package id.ac.itera.pesanlapangan

import id.ac.itera.pesanlapangan.data.PenyimpananPemesanan
import id.ac.itera.pesanlapangan.data.daftarLapangan
import id.ac.itera.pesanlapangan.model.Pemesanan
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PenyimpananPemesananTest {

    @BeforeTest
    fun siapkan() = PenyimpananPemesanan.reset()

    private fun calon(tanggal: String) = Pemesanan(
        id = 0,
        lapangan = daftarLapangan.first { it.id == 3 },
        namaPemesan = "Uji",
        tanggal = tanggal,
        jamMulai = "10.00",
        jamSelesai = "11.00"
    )

    @Test
    fun awalnyaBerisiDuabelasDataContoh() {
        assertEquals(12, PenyimpananPemesanan.daftar.value.size)
    }

    @Test
    fun tambahMemberiIdBerikutnyaDanDaftarBertambah() {
        PenyimpananPemesanan.tambah(calon("2026-11-05"))
        assertEquals(13, PenyimpananPemesanan.daftar.value.size)
        assertEquals(13, PenyimpananPemesanan.daftar.value.last().id)
        PenyimpananPemesanan.tambah(calon("2026-11-06"))
        assertEquals(14, PenyimpananPemesanan.daftar.value.last().id)
    }
}
