package id.ac.itera.pesanlapangan.data

import id.ac.itera.pesanlapangan.model.Pemesanan
import id.ac.itera.pesanlapangan.model.ringkas
import kotlinx.coroutines.CancellationException

val sumber: Sumber<Pemesanan> = SumberPemesananPalsu()

suspend fun muatAman(
    id: Int,
    sumberData: Sumber<Pemesanan> = sumber
): String =
    try {
        require(id > 0) { "id harus positif" }
        sumberData.ambil(id).ringkas()
    } catch (e: CancellationException) {
        throw e // pembatalan jangan ditelan
    } catch (e: Exception) {
        "Gagal memuat pemesanan #$id"
    }
