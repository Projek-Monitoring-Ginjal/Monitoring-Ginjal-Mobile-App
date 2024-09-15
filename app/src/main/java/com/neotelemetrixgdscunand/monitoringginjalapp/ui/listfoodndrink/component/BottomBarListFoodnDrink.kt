package com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.neotelemetrixgdscunand.monitoringginjalapp.model.NutrientItem
import com.neotelemetrixgdscunand.monitoringginjalapp.model.NutrientStatus
import com.neotelemetrixgdscunand.monitoringginjalapp.model.NutrientThresholds
import com.neotelemetrixgdscunand.monitoringginjalapp.model.getNutrientStatus

@Composable
fun BottomBarFoodSearch(
    modifier: Modifier = Modifier,
    nutrientItems: List<NutrientItem> = createDummyNutrientItems()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LegendItem(text = stringResource(R.string.belum_terpenuhi), color = Color.White)
            LegendItem(text = stringResource(R.string.terpenuhi), color = colorResource(R.color.lightGreen))
            LegendItem(text = stringResource(R.string.berlebih), color = colorResource(R.color.lightYellow))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display each nutrient row
        nutrientItems.forEach { nutrient ->
            val backgroundColor = when (nutrient.status) {
                NutrientStatus.BELUM_TERPENUHI -> colorResource(R.color.white)
                NutrientStatus.TERPENUHI -> colorResource(R.color.lightGreen)
                NutrientStatus.BERLEBIH -> colorResource(R.color.lightYellow)
            }
            NutrientRow(nutrient.name, "${nutrient.value} ${nutrient.unit}", backgroundColor)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green)
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = stringResource(R.string.simpan),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LegendItem(text: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 10.sp
        )
    }
}

@Composable
fun NutrientRow(label: String, value: String, backgroundColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}
fun createDummyNutrientItems(): List<NutrientItem> {
    return listOf(
        NutrientItem(
            name = "Kalori",
            value = 1800.0,
            unit = "kal",
            status = getNutrientStatus(1800.0, NutrientThresholds.CALORIE_THRESHOLD)
        ),
        NutrientItem(
            name = "Cairan",
            value = 2100.0,
            unit = "ml",
            status = getNutrientStatus(2100.0, NutrientThresholds.LIQUID_THRESHOLD)
        ),
        NutrientItem(
            name = "Protein",
            value = 45.0,
            unit = "mg",
            status = getNutrientStatus(45.0, NutrientThresholds.PROTEIN_THRESHOLD)
        ),
        NutrientItem(
            name = "Natrium",
            value = 2400.0,
            unit = "mg",
            status = getNutrientStatus(2400.0, NutrientThresholds.SODIUM_THRESHOLD)
        ),
        NutrientItem(
            name = "Kalium",
            value = 3000.0,
            unit = "mg",
            status = getNutrientStatus(3000.0, NutrientThresholds.POTASSIUM_THRESHOLD)
        )
    )
}

@Preview
@Composable
fun BottomBarFoodSearchPreview() {
    BottomBarFoodSearch()
}
