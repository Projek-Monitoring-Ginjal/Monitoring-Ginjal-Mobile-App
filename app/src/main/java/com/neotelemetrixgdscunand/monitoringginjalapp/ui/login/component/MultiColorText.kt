package com.neotelemetrixgdscunand.monitoringginjalapp.ui.login.component



import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.modakFamily

@Composable
fun MultiColorText(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 36.sp,
    fontFamily: FontFamily = modakFamily,
    letterSpacing: TextUnit = 0.1.sp,
    vararg textWithColors:Pair<String, Color>,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            textWithColors.forEach { (text, color) ->
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = fontSize,
                        fontFamily = fontFamily,
                        letterSpacing = letterSpacing
                    )
                ){
                    append(text)
                }
            }
        }
    )
}
