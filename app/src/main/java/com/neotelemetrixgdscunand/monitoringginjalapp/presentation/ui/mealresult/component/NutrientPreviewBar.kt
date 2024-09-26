package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green50
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey50
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.util.ListFoodnDrinkUtil

@Composable
fun NutrientPreviewBar(
    modifier: Modifier = Modifier,
    progressFraction:Float = 0f
) {

    BoxWithConstraints(
        modifier = modifier
            .background(
                color = Color.Transparent
            )
    ){
        val bulletSize = 24.dp
        val bulletPosition = remember (progressFraction) {
            val position = ((progressFraction/2f) * this.maxWidth) - (bulletSize / 2)
            if(position < 0.dp) 0.dp else position
        }
        val isMaximumExcessive = remember(progressFraction) {
            progressFraction == 2f
        }

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
                .padding(start = if(!isMaximumExcessive) bulletPosition else 0.dp)
                .size(bulletSize)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .background(
                    color = ListFoodnDrinkUtil.getColorFromGradient(progressFraction),
                    shape = CircleShape
                )
                .align(if(!isMaximumExcessive) Alignment.CenterStart else Alignment.CenterEnd)
                .zIndex(2f)
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
private fun NutrientPreviewBarPreview() {
    MonitoringGinjalAppTheme {
        NutrientPreviewBar(
            progressFraction = 1f,
        )
    }
}