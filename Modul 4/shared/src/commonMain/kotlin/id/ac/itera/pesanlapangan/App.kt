package id.ac.itera.pesanlapangan

import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import id.ac.itera.pesanlapangan.data.PenyimpananPemesanan
import id.ac.itera.pesanlapangan.ui.FormPemesananScreen
import id.ac.itera.pesanlapangan.ui.LayarDaftarPemesanan

@Composable
@Preview
fun App() {
    MaterialTheme {
        var diForm by rememberSaveable { mutableStateOf(false) }
        val daftar by PenyimpananPemesanan.daftar.collectAsState()

        if (diForm) {
            FormPemesananScreen(onKembali = { diForm = false })
        } else {
            LayarDaftarPemesanan(daftar = daftar, onTambah = { diForm = true })
        }
    }
}
