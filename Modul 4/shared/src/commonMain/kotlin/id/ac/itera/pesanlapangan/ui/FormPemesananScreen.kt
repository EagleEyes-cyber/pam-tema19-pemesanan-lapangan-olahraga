package id.ac.itera.pesanlapangan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ac.itera.pesanlapangan.data.daftarLapangan
import id.ac.itera.pesanlapangan.model.formatRupiah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPemesananScreen(
    vm: FormPemesananViewModel = viewModel { FormPemesananViewModel() },
    onKembali: () -> Unit = {}
) {
    val state by vm.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pesan Lapangan Baru") },
                navigationIcon = {
                    TextButton(onClick = onKembali) { Text("Kembali") }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            KolomTeks("Nama pemesan", state.namaPemesan, vm::ubahNama)
            Text("${state.namaPemesan.length} dari 40 karakter")

            Text("Pilih lapangan")
            PilihanLapangan(daftarLapangan, state.lapanganId, vm::pilihLapangan)
            state.lapangan?.let {
                Text("${it.jenisOlahraga}, ${formatRupiah(it.hargaPerJam)}/jam")
            }

            KolomTeks("Tanggal (contoh 2026-11-05)", state.tanggal, vm::ubahTanggal)
            KolomTeks("Jam mulai (contoh 19.00)", state.jamMulai, vm::ubahJamMulai)
            KolomTeks("Jam selesai (contoh 21.00)", state.jamSelesai, vm::ubahJamSelesai)
            KolomTeks("Catatan tambahan (boleh kosong)", state.catatan, vm::ubahCatatan)

            Button(
                onClick = vm::kirim,
                enabled = state.bisaSimpan,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.sedangMengirim) "Mengirim" else "Pesan Sekarang")
            }

            state.pesan?.let { Text(it) }
        }
    }
}
