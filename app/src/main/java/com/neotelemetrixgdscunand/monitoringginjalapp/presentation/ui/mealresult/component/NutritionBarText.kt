package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.karlaFamily
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil.roundOffDecimal

@Composable
fun NutritionBarText(
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = karlaFamily,
    letterSpacing: TextUnit = 0.1.sp,
    textAlign: TextAlign = TextAlign.Unspecified,
    nutritionalContentValue:Float = 0f,
    nutritionalThreshold:Float = 0f,
    nutritionalContentUnit:String = "",
    isNutritionAmountSufficient:Boolean = false,
    valueFontSize : TextUnit = 20.sp,
    unitFontSize : TextUnit = 16.sp
) {

    Text(
        modifier = modifier,
        textAlign = textAlign,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = if(!isNutritionAmountSufficient) Color.Red else Color.Black,
                    fontSize = valueFontSize,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = letterSpacing
                )
            ){
                append(
                    nutritionalContentValue
                    .roundOffDecimal()
                    .toString()
                )
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = valueFontSize,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = letterSpacing
                )
            ){
                append("/${nutritionalThreshold.roundOffDecimal()} ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = unitFontSize,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = letterSpacing
                )
            ){
                append(nutritionalContentUnit)
            }
        }
    )

}