package id.ac.itera.pesanlapangan

import id.ac.itera.pesanlapangan.data.PenyimpananPemesanan
import id.ac.itera.pesanlapangan.ui.FormPemesananUiState
import id.ac.itera.pesanlapangan.ui.FormPemesananViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class FormPemesananViewModelTest {

    @BeforeTest
    fun siapkan() {
        Dispatchers.setMain(StandardTestDispatcher())
        PenyimpananPemesanan.reset()
    }

    @AfterTest
    fun bersihkan() {
        Dispatchers.resetMain()
    }

    private fun FormPemesananViewModel.isiLengkap() {
        ubahNama("Bimo Auliano")
        pilihLapangan(1)
        ubahTanggal("2026-11-05")
        ubahJamMulai("19.00")
        ubahJamSelesai("21.00")
    }

    @Test
    fun ubahMengisiStateDanMembatasiNama() {
        val vm = FormPemesananViewModel()
        vm.ubahNama("a".repeat(60))
        assertEquals(40, vm.uiState.value.namaPemesan.length)
        vm.pilihLapangan(3)
        assertEquals(3, vm.uiState.value.lapanganId)
    }

    @Test
    fun kirimSebelumLengkapTidakMelakukanApaPun() = runTest {
        val vm = FormPemesananViewModel()
        vm.ubahNama("Bimo Auliano")
        vm.kirim()
        advanceUntilIdle()
        assertEquals(12, PenyimpananPemesanan.daftar.value.size)
        assertFalse(vm.uiState.value.sedangMengirim)
    }

    @Test
    fun kirimValidMenambahDataLaluMengosongkanForm() = runTest {
        val vm = FormPemesananViewModel()
        vm.isiLengkap()
        vm.kirim()
        assertTrue(vm.uiState.value.sedangMengirim)
        assertFalse(vm.uiState.value.bisaSimpan)

        advanceUntilIdle()

        val daftar = PenyimpananPemesanan.daftar.value
        assertEquals(13, daftar.size)
        assertEquals("Bimo Auliano", daftar.last().namaPemesan)
        assertNull(daftar.last().catatanTambahan)
        assertEquals(FormPemesananUiState(pesan = "Pemesanan terkirim"), vm.uiState.value)
    }

    @Test
    fun kirimDuaKaliCepatHanyaMenambahSatu() = runTest {
        val vm = FormPemesananViewModel()
        vm.isiLengkap()
        vm.kirim()
        vm.kirim()
        advanceUntilIdle()
        assertEquals(13, PenyimpananPemesanan.daftar.value.size)
    }

    @Test
    fun mengetikLagiMenghapusPesanLama() = runTest {
        val vm = FormPemesananViewModel()
        vm.isiLengkap()
        vm.kirim()
        advanceUntilIdle()
        assertEquals("Pemesanan terkirim", vm.uiState.value.pesan)
        vm.ubahNama("Jova")
        assertNull(vm.uiState.value.pesan)
    }

    @Test
    fun catatanDiisiDisimpan() = runTest {
        val vm = FormPemesananViewModel()
        vm.isiLengkap()
        vm.ubahCatatan("Pinjam rompi 2 set")
        vm.kirim()
        advanceUntilIdle()
        assertEquals("Pinjam rompi 2 set", PenyimpananPemesanan.daftar.value.last().catatanTambahan)
    }
}
