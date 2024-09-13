package com.neotelemetrixgdscunand.monitoringginjalapp.ui.login.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.karlaFamily

@Composable
fun HeadingText(
    modifier: Modifier = Modifier,
    text:String = "",
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Center,
    fontFamily: FontFamily = karlaFamily,
    fontWeight: FontWeight = FontWeight.Bold,
    letterSpacing: TextUnit = (-0.3f).sp,
    color: Color = Grey20,
    lineHeight:TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        color = color,
        lineHeight = lineHeight
    )
}
