package com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.Button
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.FormField
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.TopBarMandeh
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
                    text = stringResource(R.string.pengaturan_makan_saya_hari_ini),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.berat_badan_kering_saya),
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
        }
        val textState = remember { mutableStateOf("") }
        FormField(
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = stringResource(R.string.tulis_angka),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 16.dp),
                text = stringResource(R.string.simpan),
                textColor = Color.White,
                fontSize = 18f,
                fontWeight = FontWeight.Normal,
                padding = 20.dp,
                onClick = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PengaturanMakanPagePreview() {
    PengaturanMakanPage()
}
