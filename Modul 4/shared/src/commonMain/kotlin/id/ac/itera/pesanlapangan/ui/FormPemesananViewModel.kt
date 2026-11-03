package id.ac.itera.pesanlapangan.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.itera.pesanlapangan.data.PenyimpananPemesanan
import id.ac.itera.pesanlapangan.model.Pemesanan
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormPemesananViewModel(
    private val penyimpanan: PenyimpananPemesanan = PenyimpananPemesanan
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormPemesananUiState())
    val uiState: StateFlow<FormPemesananUiState> = _uiState.asStateFlow()

    fun ubahNama(teks: String) =
        _uiState.update { it.copy(namaPemesan = teks.take(40), pesan = null) }

    fun pilihLapangan(id: Int) =
        _uiState.update { it.copy(lapanganId = id, pesan = null) }

    fun ubahTanggal(teks: String) =
        _uiState.update { it.copy(tanggal = teks, pesan = null) }

    fun ubahJamMulai(teks: String) =
        _uiState.update { it.copy(jamMulai = teks, pesan = null) }

    fun ubahJamSelesai(teks: String) =
        _uiState.update { it.copy(jamSelesai = teks, pesan = null) }

    fun ubahCatatan(teks: String) =
        _uiState.update { it.copy(catatan = teks, pesan = null) }

    fun kirim() {
        val form = _uiState.value
        if (!form.bisaSimpan) return
        val lapangan = form.lapangan ?: return

        _uiState.update { it.copy(sedangMengirim = true) }
        viewModelScope.launch {
            delay(800)
            penyimpanan.tambah(
                Pemesanan(
                    id = 0,
                    lapangan = lapangan,
                    namaPemesan = form.namaPemesan.trim(),
                    tanggal = form.tanggal.trim(),
                    jamMulai = form.jamMulai.trim(),
                    jamSelesai = form.jamSelesai.trim(),
                    catatanTambahan = form.catatan.takeIf { it.isNotBlank() }?.trim()
                )
            )
            _uiState.value = FormPemesananUiState(pesan = "Pemesanan terkirim")
        }
    }
}
