package com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.Button
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.FormField
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.component.NutrientCard
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.viewmodel.PengaturanMakanViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Typography

@Composable
fun PengaturanMakanPage(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit = {},
    viewModel: PengaturanMakanViewModel = viewModel()
) {
    val textState = viewModel.textState.collectAsState().value
    val showDialog = viewModel.showDialog.collectAsState().value
    val bbk = viewModel.bbk.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
    ) {
        Column(
            modifier = modifier.padding(start = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.pengaturan_makan_saya_hari_ini),
                style = Typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.berat_badan_kering_saya),
                style = Typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        FormField(
            value = textState,
            onValueChange = { viewModel.onTextChange(it) },
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
                    viewModel.onSaveClicked()
                }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.onDismissDialog() },
                text = {
                    val calories = (bbk * 35).toInt()
                    val liquid = (bbk * 0.8 + 500).toInt()
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
                            viewModel.onConfirmDialog()
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

@Preview
@Composable
fun PengaturanMakanPagePreview() {
    PengaturanMakanPage()
}
