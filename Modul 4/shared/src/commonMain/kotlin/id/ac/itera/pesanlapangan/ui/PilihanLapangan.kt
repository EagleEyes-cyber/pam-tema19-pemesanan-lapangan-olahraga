package id.ac.itera.pesanlapangan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.ac.itera.pesanlapangan.model.Lapangan

@Composable
fun PilihanLapangan(
    daftar: List<Lapangan>,
    terpilihId: Int?,
    onPilih: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(daftar, key = { it.id }) { lapangan ->
            FilterChip(
                selected = lapangan.id == terpilihId,
                onClick = { onPilih(lapangan.id) },
                label = { Text(lapangan.nama) }
            )
        }
    }
}
