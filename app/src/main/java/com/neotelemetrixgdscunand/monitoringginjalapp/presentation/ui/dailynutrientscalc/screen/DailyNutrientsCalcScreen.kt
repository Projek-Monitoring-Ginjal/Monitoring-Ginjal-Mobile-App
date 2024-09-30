package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Typography
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component.Button
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component.FormField
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component.NutrientNeedsDialog
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.viewmodel.DailyNutrientCalcUtilVM
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent

@Composable
fun DailyNutrientsCalcScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit = {},
    viewModel: DailyNutrientCalcUtilVM = hiltViewModel()
) {
    val textState = viewModel.textState
    val showDialog = viewModel.showDialog
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{
            when(it){
                is UIEvent.ShowToast -> Toast.makeText(
                    context,
                    it.message.getValue(context),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {}
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
    ) {

        if(viewModel.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Green20
            )
        }else{
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
        }
        if (showDialog) {
            Dialog(onDismissRequest = viewModel::onDismissDialog) {
                NutrientNeedsDialog(
                    nutritionNeeds = viewModel.nutritionNeeds,
                    onConfirm = {
                        viewModel.onDismissDialog()
                        onNavigate()
                    }
                )
            }

        }
    }
}


@Preview
@Composable
fun PengaturanMakanPagePreview() {
    DailyNutrientsCalcScreen()
}
