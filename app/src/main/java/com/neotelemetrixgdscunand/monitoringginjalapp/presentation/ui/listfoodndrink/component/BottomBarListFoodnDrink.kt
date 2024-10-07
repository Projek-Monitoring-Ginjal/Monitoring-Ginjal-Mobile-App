package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.NutritionBarText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil.determineNutritionPreviewBarColor

@SuppressLint("DefaultLocale")
@Composable
fun BottomBarFoodSearch(
    modifier: Modifier = Modifier,
    nutrition: NutritionEssential = NutritionEssential(),
    dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold,
    onSaveClick: () -> Unit = {},
) {
    val context = LocalContext.current
    val nutritionToThreshold = remember(
        dailyNutrientNeedsThreshold,
        nutrition
    ){
        listOf(
            nutrition.calorie to
                    dailyNutrientNeedsThreshold.caloriesThreshold,
            nutrition.fluid to
                    dailyNutrientNeedsThreshold.fluidThreshold,
            nutrition.protein to
                    dailyNutrientNeedsThreshold.proteinThreshold,
            nutrition.sodium to
                    dailyNutrientNeedsThreshold.sodiumThreshold,
            nutrition.potassium to
                    dailyNutrientNeedsThreshold.potassiumThreshold
        )
    }

    Column(
        modifier = modifier
            .background(color = colorResource(R.color.white))
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LegendItem(text = stringResource(R.string.harus_dipenuhi), color = colorResource(R.color.lightGreen))
            LegendItem(text = stringResource(R.string.tidak_boleh_berlebih), color = colorResource(R.color.lightYellow))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display each nutrient row
        /*nutrientItems.forEach { nutrient ->
            val backgroundColor = when (nutrient.status) {
                NutrientStatus.BELUM_TERPENUHI -> colorResource(R.color.white)
                NutrientStatus.TERPENUHI -> colorResource(R.color.lightGreen)
                NutrientStatus.BERLEBIH -> colorResource(R.color.lightYellow)
            }
            NutrientRow(nutrient.name,String.format("%.2f", nutrient.value) + " ${nutrient.unit}", backgroundColor)
        }*/
        nutritionToThreshold.forEach { it ->
            val (nutrient, threshold) = it

            NutrientRow(
                label = nutrient.name.getValue(context),
                nutritionalThreshold = threshold,
                nutritionalContentUnit = nutrient.unit.getValue(context),
                nutritionalContentValue = nutrient.amount,
                isNutritionAmountSufficient = nutrient.checkIsSufficientAmount(threshold),
                backgroundColor = nutrient.determineNutritionPreviewBarColor(),
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSaveClick,
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
                .background(color, shape = RoundedCornerShape(5.dp))
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(5.dp)
                )
        )

        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = Color.Black,
            fontSize = 10.sp
        )
    }
}

@Composable
fun NutrientRow(
    label: String,
    nutritionalContentValue:Float,
    nutritionalThreshold:Float,
    nutritionalContentUnit:String,
    isNutritionAmountSufficient:Boolean,
    backgroundColor:Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeadingText(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        NutritionBarText(
            modifier = Modifier.padding(horizontal = 8.dp),
            nutritionalThreshold = nutritionalThreshold,
            nutritionalContentValue = nutritionalContentValue,
            nutritionalContentUnit = nutritionalContentUnit,
            isNutritionAmountSufficient = isNutritionAmountSufficient
        )
    }
}

@Preview
@Composable
fun BottomBarFoodSearchPreview() {
    BottomBarFoodSearch(
        dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
    )
}
