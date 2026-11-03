package id.ac.itera.pesanlapangan.data

import id.ac.itera.pesanlapangan.model.Pemesanan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object PenyimpananPemesanan {
    private val _daftar = MutableStateFlow(daftarPemesananContoh())
    val daftar: StateFlow<List<Pemesanan>> = _daftar.asStateFlow()

    fun tambah(calon: Pemesanan) = _daftar.update { lama ->
        val idBaru = (lama.maxOfOrNull { it.id } ?: 0) + 1
        lama + calon.copy(id = idBaru)
    }

    internal fun reset() {
        _daftar.value = daftarPemesananContoh()
    }
}
