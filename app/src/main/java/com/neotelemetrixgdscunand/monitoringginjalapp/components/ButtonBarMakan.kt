package com.neotelemetrixgdscunand.monitoringginjalapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBarMakan() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PortionButton(text = "1/4 porsi")
            PortionButton(text = "1/2 porsi")
            PortionButton(text = "1 porsi")
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButton(
                text = "Batal",
                backgroundColor = Color.White,
                textColor = Color(0xFF2E7D32),
                borderColor = Color(0xFF2E7D32),
                modifier = Modifier.weight(1f) 
            )
            ActionButton(
                text = "Simpan",
                backgroundColor = Color(0xFF2E7D32),
                textColor = Color.White,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PortionButton(text: String) {
    Button(
        onClick = { /* TODO: Handle click */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFFF9A825)
        ),
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, Color(0xFFF9A825)),
        modifier = Modifier
            .height(48.dp)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun ActionButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    borderColor: Color? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Button(
        onClick = { /* TODO: Handle click */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(10.dp),
        border = borderColor?.let { BorderStroke(1.dp, it) },
        modifier = modifier
            .height(48.dp)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarMakanPreview() {
    BottomBarMakan()
}
