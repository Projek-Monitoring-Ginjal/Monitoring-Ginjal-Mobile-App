package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutrientPreviewCard(
    modifier: Modifier = Modifier,
    nutrientContentName:String,
    progressFraction:Float = 0.0f,
    isLessAmountSufficient:Boolean = false
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(size = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,

        ),
    ) {
        HeadingText(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 16.dp),
            text = nutrientContentName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        NutrientPreviewBar(
            isLessAmountSufficient = isLessAmountSufficient,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp),
            progressFraction = progressFraction,
        )
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .fillMaxWidth(),
        ){
            HeadingText(
                modifier = Modifier.align(Alignment.CenterStart),
                text = stringResource(R.string.belum_terpenuhi),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            HeadingText(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.terpenuhi),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            HeadingText(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = stringResource(R.string.berlebih),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NutrientPreviewCardPreview() {
    MonitoringGinjalAppTheme {
        NutrientPreviewCard(
            modifier = Modifier.padding(16.dp),
            progressFraction = 1f,
            nutrientContentName = "Kalori"
        )
    }
}