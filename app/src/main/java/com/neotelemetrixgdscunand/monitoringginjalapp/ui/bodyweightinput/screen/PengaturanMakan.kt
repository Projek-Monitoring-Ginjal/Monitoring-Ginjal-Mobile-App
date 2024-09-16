package com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.NutrientCard
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Typography

@Composable
fun PengaturanMakanPage(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit = {},
) {
    var textState by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var bbk by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //TopBarMandeh()
        Column(
            modifier = modifier.padding(start = 16.dp)
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

        FormField(
            value = textState,
            onValueChange = { textState = it },
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
                onClick = {
                    bbk = textState.toDoubleOrNull() ?: 0.0
                    if (bbk > 0) {
                        showDialog = true
                    }
                }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                text = {
                    val calories = (bbk * 35).toInt()
                    val liquid = (bbk * 0.08 * 1000 + 500).toInt()
                    val potassium = 2500
                    val sodium = 200
                    val protein = (bbk * 1.2).toInt()

                    NutrientCard(
                        calories = calories,
                        liquid = liquid,
                        protein = protein,
                        sodium = sodium,
                        potassium = potassium
                    )
                },
                confirmButton = {
                    Button(
                        text = "OK",
                        onClick = {
                            showDialog = false
                            onNavigate()
                        },
                        textColor = Color.White,
                        fontSize = 18f,
                        fontWeight = FontWeight.Normal,
                        padding = 16.dp
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PengaturanMakanPagePreview() {
    PengaturanMakanPage()
}

