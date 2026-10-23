package id.ac.itera.pesanlapangan

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import id.ac.itera.pesanlapangan.data.demoStatus
import id.ac.itera.pesanlapangan.ui.LayarDaftarPemesanan

@Composable
@Preview
fun App() {
    LaunchedEffect(Unit) {
        demoStatus()
    }
    MaterialTheme {
        LayarDaftarPemesanan()
    }
}
