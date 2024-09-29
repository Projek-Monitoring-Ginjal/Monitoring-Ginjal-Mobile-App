package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential

@Composable
fun NutrientNeedsDialog(
    modifier: Modifier = Modifier,
    nutritionNeeds: NutritionEssential,
    onConfirm: () -> Unit = {},
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

            NutrientInfoRow(
                stringResource(R.string.kalori),
                "${nutritionNeeds.calorie.amount} kkal"
            )
            NutrientInfoRow(
                stringResource(R.string.cairan),
                "${nutritionNeeds.fluid.amount} ml"
            )
            NutrientInfoRow(
                stringResource(R.string.protein),
                "${nutritionNeeds.protein.amount} gr"
            )
            NutrientInfoRow(
                stringResource(R.string.natrium),
                "${nutritionNeeds.sodium.amount} mg"
            )
            NutrientInfoRow(
                stringResource(R.string.kalium),
                "${nutritionNeeds.potassium.amount} mg"
            )
            Button(
                text = "OK",
                onClick = onConfirm,
                textColor = Color.White,
                fontSize = 18f,
                fontWeight = FontWeight.Normal,
                padding = 16.dp
            )
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
    NutrientNeedsDialog(
        nutritionNeeds = NutritionEssential()
    )
}
