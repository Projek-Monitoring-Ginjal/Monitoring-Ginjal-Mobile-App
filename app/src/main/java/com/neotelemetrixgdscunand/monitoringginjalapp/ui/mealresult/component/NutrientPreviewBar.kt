package com.neotelemetrixgdscunand.monitoringginjalapp.ui.mealresult.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Green50
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey50
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Yellow40

@Composable
fun NutrientPreviewBar(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = Color.Transparent
            )
    ){
        this
        Box(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White,
                            Green50,
                            Yellow40
                        )
                    ),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(size = 16.dp),
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Grey50,
                            Green50,
                            Yellow40
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .background(
                    color = Yellow40,
                    shape = CircleShape
                )
                .align(Alignment.CenterEnd)
                .zIndex(2f)
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
private fun NutrientPreviewBarPreview() {
    MonitoringGinjalAppTheme {
        NutrientPreviewBar()
    }
}