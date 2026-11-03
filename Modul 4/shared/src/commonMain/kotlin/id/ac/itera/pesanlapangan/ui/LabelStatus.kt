package id.ac.itera.pesanlapangan.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.ac.itera.pesanlapangan.model.StatusPemesanan
import id.ac.itera.pesanlapangan.model.label

private fun StatusPemesanan.warnaLatar(): Color = when (this) {
    StatusPemesanan.MenungguKonfirmasi -> Color(0xFFE0E0E0)
    is StatusPemesanan.Dikonfirmasi -> Color(0xFFFFF3C4)
    is StatusPemesanan.Selesai -> Color(0xFFD7F5DD)
}

private fun StatusPemesanan.warnaTeks(): Color = when (this) {
    StatusPemesanan.MenungguKonfirmasi -> Color(0xFF424242)
    is StatusPemesanan.Dikonfirmasi -> Color(0xFF7A5B00)
    is StatusPemesanan.Selesai -> Color(0xFF1B5E20)
}

@Composable
fun LabelStatus(status: StatusPemesanan, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = status.warnaLatar(),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = status.label(),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = status.warnaTeks(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}
