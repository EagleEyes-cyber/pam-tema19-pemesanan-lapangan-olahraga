package id.ac.itera.pesanlapangan.ui

import id.ac.itera.pesanlapangan.data.daftarLapangan
import id.ac.itera.pesanlapangan.model.Lapangan

data class FormPemesananUiState(
    val namaPemesan: String = "",
    val lapanganId: Int? = null,
    val tanggal: String = "",
    val jamMulai: String = "",
    val jamSelesai: String = "",
    val catatan: String = "",
    val sedangMengirim: Boolean = false,
    val pesan: String? = null
) {
    val lapangan: Lapangan?
        get() = daftarLapangan.firstOrNull { it.id == lapanganId }

    val bisaSimpan: Boolean
        get() = namaPemesan.isNotBlank() &&
                lapanganId != null &&
                tanggal.isNotBlank() &&
                jamMulai.isNotBlank() &&
                jamSelesai.isNotBlank() &&
                !sedangMengirim
}
