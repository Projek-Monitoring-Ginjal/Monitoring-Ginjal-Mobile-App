package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16
) {

    val allowedCharacterPattern = remember {
        "^[0-9]*[,]{0,1}[0-9]*$".toRegex()
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (allowedCharacterPattern.matches(it)) {
                onValueChange(it)
            }
        },
        shape = RoundedCornerShape(cornerRadius.dp),
        placeholder = { Text(text = placeholder, color = Color.Gray, modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
        textStyle = TextStyle(
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
        ),

        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.white))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
        )
    )
}

@Preview
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
