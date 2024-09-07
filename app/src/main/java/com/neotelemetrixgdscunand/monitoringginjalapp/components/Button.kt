package com.neotelemetrixgdscunand.monitoringginjalapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF005F40),
    textColor: Color = Color.White,
    fontSize: Float = 16f,
    fontWeight: FontWeight = FontWeight.Bold,
    padding: Dp = 16.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize.sp,
            fontWeight = fontWeight
        )
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    Button(
        modifier = Modifier.fillMaxWidth(),
        text = "Simpan",
        textColor = Color.White,
        fontSize = 18f,
        fontWeight = FontWeight.Normal,
        padding = 20.dp,
        onClick = { }
    )
}