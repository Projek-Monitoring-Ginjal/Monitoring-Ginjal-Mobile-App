package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText

@Composable
fun NutritionStatBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Green40,
    nutritionalContentName:String = "",
    nutritionalContentValue:Float = 0f,
    nutritionalThreshold:Float = 0f,
    nutritionalContentUnit:String = "",
    isNutritionAmountSufficient:Boolean = false
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeadingText(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                text = nutritionalContentName,
                color =  Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            NutritionBarText(
                modifier = Modifier.padding(horizontal = 12.dp),
                nutritionalThreshold = nutritionalThreshold,
                nutritionalContentValue = nutritionalContentValue,
                nutritionalContentUnit = nutritionalContentUnit,
                isNutritionAmountSufficient = isNutritionAmountSufficient
            )
        }
    }
}

@Preview
@Composable
private fun NutritionStatBarPreview() {
    MonitoringGinjalAppTheme {
        NutritionStatBar(
            nutritionalContentName = "Kalori",
            nutritionalContentValue = 2400f,
            nutritionalContentUnit = "kal"
        )
    }
}