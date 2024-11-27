package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.karlaFamily

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    backgroundColor: Color = Green20,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    text:String = stringResource(id = R.string.simpan),
    textColor:Color = Color.White,
    fontFamily: FontFamily = karlaFamily,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize:TextUnit = 16.sp,
    letterSpacing: TextUnit = 0.1.sp,
    enabled:Boolean = true,
    contentPadding:PaddingValues = PaddingValues(16.dp),
    lineHeight:TextUnit = TextUnit.Unspecified
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = shape,
        contentPadding = contentPadding
    ) {
        HeadingText(
            text = text,
            color = textColor,
            fontFamily = fontFamily,
            lineHeight = lineHeight,
            fontWeight = fontWeight,
            letterSpacing = letterSpacing,
            fontSize = fontSize
        )
    }
}


@Preview
@Composable
private fun StyledButtonPreview() {
    MonitoringGinjalAppTheme {
        StyledButton()
    }
}