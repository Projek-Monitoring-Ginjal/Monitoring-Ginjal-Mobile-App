package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme

@Composable
fun NutritionStatBar(
    modifier: Modifier = Modifier,
    color: Color = Green40,
    nutritionalContentName:String = "",
    nutritionalContentValue:Int = -1,
    nutritionalContentUnit:String = ""
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Row {
            HeadingText(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                text = nutritionalContentName,
                color =  Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            HeadingText(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                text = "$nutritionalContentValue $nutritionalContentUnit",
                color =  Color.Black,
                fontWeight = FontWeight.Bold
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
            nutritionalContentValue = 2400,
            nutritionalContentUnit = "kal"
        )
    }
}