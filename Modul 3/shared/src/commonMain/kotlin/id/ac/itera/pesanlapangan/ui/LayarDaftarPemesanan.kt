package id.ac.itera.pesanlapangan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.ac.itera.pesanlapangan.data.SumberPemesananPalsu
import id.ac.itera.pesanlapangan.model.FilterStatus
import id.ac.itera.pesanlapangan.model.Pemesanan
import id.ac.itera.pesanlapangan.model.hitungPerStatus
import id.ac.itera.pesanlapangan.model.saringStatus
import id.ac.itera.pesanlapangan.model.urutkanJadwal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayarDaftarPemesanan(
    sumber: SumberPemesananPalsu = remember { SumberPemesananPalsu() },
    onTambah: () -> Unit = {}
) {
    var semua by remember { mutableStateOf<List<Pemesanan>?>(null) }
    var filter by remember { mutableStateOf(FilterStatus.Semua) }
    var terbaruDulu by remember { mutableStateOf(true) }

    LaunchedEffect(sumber) {
        semua = sumber.ambilSemua()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pesan Lapangan ITERA") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onTambah) {
                Text("+  Pesan Lapangan")
            }
        }
    ) { padding ->
        val data = semua
        if (data == null) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        val jumlah = remember(data) { data.hitungPerStatus() }
        val tampil = remember(data, filter, terbaruDulu) {
            data.saringStatus(filter).urutkanJadwal(terbaruDulu)
        }

        Column(Modifier.fillMaxSize().padding(padding)) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(FilterStatus.entries) { pilihan ->
                    FilterChip(
                        selected = filter == pilihan,
                        onClick = { filter = pilihan },
                        label = { Text("${pilihan.judul} (${jumlah[pilihan] ?: 0})") }
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${tampil.size} pemesanan",
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = { terbaruDulu = !terbaruDulu }) {
                    Text(if (terbaruDulu) "Urutan: Terbaru di atas" else "Urutan: Terlama di atas")
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 88.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tampil, key = { it.id }) { pemesanan ->
                    KartuPemesanan(pemesanan)
                }
            }
        }
    }
}
