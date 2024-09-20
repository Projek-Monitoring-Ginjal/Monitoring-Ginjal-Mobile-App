package com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R

@Composable
fun NutrientCard(
    calories: Int = 2310,
    liquid: Int = 600,
    protein: Int = 79200,
    sodium: Int = 2000,
    potassium: Int = 2000,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.kebutuhan_makan_saya_sehari),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            NutrientInfoRow(stringResource(R.string.kalori), "$calories kkal")
            NutrientInfoRow(stringResource(R.string.cairan), "$liquid ml")
            NutrientInfoRow(stringResource(R.string.protein), "$protein gr")
            NutrientInfoRow(stringResource(R.string.natrium), "$sodium mg")
            NutrientInfoRow(stringResource(R.string.kalium), "$potassium mg")

        }
    }
}

@Composable
fun NutrientInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.yellow)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            color = Color.Black,
            fontSize = 12.sp
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun NutrientCardPreview() {
    NutrientCard()
}
