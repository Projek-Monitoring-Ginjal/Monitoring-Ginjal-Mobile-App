package com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(cornerRadius.dp),
        placeholder = { Text(text = placeholder, color = Color.Gray, modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
        textStyle = TextStyle(
            fontSize = 16.sp, // Adjust the font size if necessary
            textAlign = TextAlign.Center // Center-align the text
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun FormFieldPreview() {
    val textState = remember { mutableStateOf("") }

    FormField(
        value = textState.value,
        onValueChange = { textState.value = it },
        placeholder = "Tulis angka",
        modifier = Modifier.fillMaxWidth()
    )
}