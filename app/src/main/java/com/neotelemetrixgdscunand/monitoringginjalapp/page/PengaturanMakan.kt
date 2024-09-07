package com.neotelemetrixgdscunand.monitoringginjalapp.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.components.Button
import com.neotelemetrixgdscunand.monitoringginjalapp.components.FormField
import com.neotelemetrixgdscunand.monitoringginjalapp.components.NutrientCard
import com.neotelemetrixgdscunand.monitoringginjalapp.components.TopBarMandeh
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.AppText
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Typography

@Composable
fun PengaturanMakanPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopBarMandeh()
        Column(
            modifier=modifier
                .padding(start = 16.dp)
        ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = AppText.pengaturanMakanTitle,
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = AppText.beratBadanKering,
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
        }
        val textState = remember { mutableStateOf("") }
        FormField(
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = AppText.tulisAngkaPlaceholder,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        NutrientCard()
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 16.dp),
                text = AppText.simpanButtonText,
                textColor = Color.White,
                fontSize = 18f,
                fontWeight = FontWeight.Normal,
                padding = 20.dp,
                onClick = { }
            )
        }
    }
}

@Preview
@Composable
private fun PengaturanMakanPagePreview() {
    PengaturanMakanPage()
}
