package com.neotelemetrixgdscunand.monitoringginjalapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R

val protein = listOf(
    ProteinItem(R.drawable.nasi_putih, "Nasi Putih", "204 kalori", "158 gr"),
    ProteinItem(R.drawable.daging_merah, "Daging Merah", "256 kalori", "170 gr"),
    ProteinItem(R.drawable.kentang, "Kentang", "90 kalori", "100 gr"),
    ProteinItem(R.drawable.kentang, "Kentang", "90 kalori", "100 gr"),
    ProteinItem(R.drawable.kentang, "Kentang", "90 kalori", "100 gr"),


)

data class ProteinItem(val imageResId: Int, val name: String, val calories: String, val weight: String)

@Composable
fun ProteinList() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6E9B2)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Color(0xFFF6E9B2)) // Sesuaikan warna background
                .padding(16.dp)
                .heightIn(max = 240.dp)
        ) {
            items(protein) { proteinItem ->
                ProteinItemRow(proteinItem)
            }
        }
    }
}

@Composable
fun ProteinItemRow(proteinItem: ProteinItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6E9B2), shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = proteinItem.imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = proteinItem.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "${proteinItem.calories} | ${proteinItem.weight}",
                fontSize = 14.sp
            )
        }

        Checkbox(
            checked = false,
            onCheckedChange = { /* handle check state */ },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
private fun ProteinListPreview() {
    ProteinList()

}