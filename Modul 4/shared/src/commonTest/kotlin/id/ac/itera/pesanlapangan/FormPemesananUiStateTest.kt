package id.ac.itera.pesanlapangan

import id.ac.itera.pesanlapangan.ui.FormPemesananUiState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FormPemesananUiStateTest {

    private val lengkap = FormPemesananUiState(
        namaPemesan = "Bimo Auliano",
        lapanganId = 1,
        tanggal = "2026-11-05",
        jamMulai = "19.00",
        jamSelesai = "21.00"
    )

    @Test
    fun formKosongTidakBisaDisimpan() {
        assertFalse(FormPemesananUiState().bisaSimpan)
    }

    @Test
    fun formLengkapBisaDisimpan() {
        assertTrue(lengkap.bisaSimpan)
    }

    @Test
    fun setiapIsianWajibDiperiksa() {
        assertFalse(lengkap.copy(namaPemesan = "  ").bisaSimpan)
        assertFalse(lengkap.copy(lapanganId = null).bisaSimpan)
        assertFalse(lengkap.copy(tanggal = "").bisaSimpan)
        assertFalse(lengkap.copy(jamMulai = "").bisaSimpan)
        assertFalse(lengkap.copy(jamSelesai = "").bisaSimpan)
    }

    @Test
    fun catatanBolehKosong() {
        assertTrue(lengkap.copy(catatan = "").bisaSimpan)
    }

    @Test
    fun saatMengirimTombolNonaktif() {
        assertFalse(lengkap.copy(sedangMengirim = true).bisaSimpan)
    }

    @Test
    fun lapanganDihitungDariId() {
        assertEquals("Futsal", lengkap.lapangan?.jenisOlahraga)
        assertNull(lengkap.copy(lapanganId = 99).lapangan)
    }
}
