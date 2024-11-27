package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component.NutrientNeedsDialog
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.ConfirmationDialog
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.InputUrineForm
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.MealDayTab
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.NutrientPreviewCard
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.NutritionStatBar
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil.determineIsNutritionLessAmountSufficient
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil.determineNutritionPreviewBarColor
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.viewmodel.MealResultViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent

@Composable
fun MealResultScreen(
    modifier: Modifier = Modifier,
    viewModel: MealResultViewModel = hiltViewModel(),
    onAddMeals: (DayOptions, HemodialisaType) -> Unit = {_, _ -> },
    onFinish: () -> Unit = { }
) {

    val pairsData = remember(
        viewModel.dayOptions,
        viewModel.dailyNutrientFourDays,
        viewModel.dailyNutrientNeedsThresholdFourDays
    ) {
        MealResultUtil.calculateDailyNutritionAmountAndThreshold(
            viewModel.dailyNutrientNeedsThresholdFourDays,
            viewModel.dailyNutrientFourDays,
            viewModel.dayOptions
        )
    }


    val context = LocalContext.current


    val isUrineForCurrentDayHasNotBeenSaved by remember(pairsData){
        derivedStateOf{
            pairsData == null
        }
    }

    //val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

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

    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp),

    ) {
        if(viewModel.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Green20
            )
        }else {


            //pairsData is already check not null
            val thresholds = pairsData?.first
            val nutritionTotalInfo = pairsData?.second

            val nutritionToThreshold = remember(
                thresholds,
                nutritionTotalInfo
            ){
                if(thresholds == null || nutritionTotalInfo == null) return@remember null

                listOf(
                    nutritionTotalInfo.calorie to
                            thresholds.caloriesThreshold,
                    nutritionTotalInfo.fluid to
                            thresholds.fluidThreshold,
                    nutritionTotalInfo.protein to
                            thresholds.proteinThreshold,
                    nutritionTotalInfo.sodium to
                            thresholds.sodiumThreshold,
                    nutritionTotalInfo.potassium to
                            thresholds.potassiumThreshold
                )
            }

            val nutritionToProgressFraction = remember(
                thresholds,
                nutritionTotalInfo
            ){
                if(thresholds == null || nutritionTotalInfo == null) return@remember null

                listOf(
                    nutritionTotalInfo.calorie to MealResultUtil.calculateProgressFraction(
                        nutritionTotalInfo.calorie.amount,
                        thresholds.caloriesThreshold
                    ),
                    nutritionTotalInfo.fluid to MealResultUtil.calculateProgressFraction(
                        nutritionTotalInfo.fluid.amount,
                        thresholds.fluidThreshold
                    ),
                    nutritionTotalInfo.protein to MealResultUtil.calculateProgressFraction(
                        nutritionTotalInfo.protein.amount,
                        thresholds.proteinThreshold
                    ),
                    nutritionTotalInfo.sodium to MealResultUtil.calculateProgressFraction(
                        nutritionTotalInfo.sodium.amount,
                        thresholds.sodiumThreshold
                    ),
                    nutritionTotalInfo.potassium to MealResultUtil.calculateProgressFraction(
                        nutritionTotalInfo.potassium.amount,
                        thresholds.potassiumThreshold
                    )
                )
            }

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 24.dp, top = 20.dp),
                modifier = Modifier.weight(1f)
            ) {

                item{
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        viewModel.tabsTextResId.forEachIndexed { index, tabTextResId ->
                            MealDayTab(
                                isSelected = viewModel.selectedTabIndex == index,
                                onClick = {
                                    viewModel.changeTab(index)
                                },
                                text = stringResource(id = tabTextResId)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                }

                if(isUrineForCurrentDayHasNotBeenSaved){
                    item {
                        HeadingText(
                            text = stringResource(R.string.urine_saya_dalam_24_jam_sebelumnya),
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        InputUrineForm(
                            textState = viewModel.urineInputText,
                            onTextChange = viewModel::onUrineInputTextChange,
                            onSaveClicked = viewModel::onDialogInputUrineConfirmationShown
                        )

                    }

                }else if(nutritionToThreshold != null && nutritionToProgressFraction != null){
                    item {
                        HeadingText(
                            text = stringResource(R.string.hasil_makan_hari_ini),
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    itemsIndexed(nutritionToThreshold){index, item ->

                        val (nutrient, threshold) = item
                        val isNutritionAmountSufficient = nutrient.checkIsSufficientAmount(threshold)

                        NutritionStatBar(
                            backgroundColor = remember {
                                nutrient.determineNutritionPreviewBarColor()
                            },
                            nutritionalContentName = nutrient.name.getValue(context),
                            nutritionalContentValue = nutrient.amount,
                            nutritionalContentUnit = nutrient.unit.getValue(context),
                            nutritionalThreshold = threshold,
                            isNutritionAmountSufficient = isNutritionAmountSufficient
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    items(nutritionToProgressFraction){ item ->
                        val (nutrient, progressFraction) = item

                        NutrientPreviewCard(
                            nutrientContentName = nutrient.name.getValue(context),
                            progressFraction = progressFraction,
                            isLessAmountSufficient = remember(nutrient) { return@remember nutrient.determineIsNutritionLessAmountSufficient() }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }


            }

            BottomButtons(
                onAddingMeals = {
                    onAddMeals(viewModel.dayOptions, viewModel.hemodialisaType)
                },
                onFinish = onFinish,
                isAddButtonEnabled = !isUrineForCurrentDayHasNotBeenSaved
            )
        }

        ConfirmationDialog(
            onDismiss = viewModel::onDialogInputUrineConfirmationDismiss,
            onConfirm = viewModel::inputUrine,
            isShown = viewModel.isDialogInputUrineConfirmationIsShown,
            text = stringResource(R.string.yakin_ingin_menyimpan)
        )

        viewModel.nutrientNeedsThresholdDialogContent?.let {
            Dialog(onDismissRequest = viewModel::onDialogNutrientNeedsThresholdDismiss) {
                NutrientNeedsDialog(
                    nutritionNeeds = it,
                    onConfirm = viewModel::onDialogNutrientNeedsThresholdDismiss
                )
            }
        }

    }

}

@Composable
private fun BottomButtons(
    onAddingMeals:() -> Unit = {},
    onFinish:()->Unit = {},
    isAddButtonEnabled:Boolean = false,
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(top = 20.dp, bottom = 16.dp)
    ) {
        StyledButton(
            modifier = Modifier
                .weight(1f)
                .border(width = 2.dp, color = Green20, shape = RoundedCornerShape(16.dp)),
            backgroundColor = Color.White,
            textColor = Green20,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            fontSize = 16.sp,
            enabled = isAddButtonEnabled,
            fontWeight = FontWeight.Medium,
            text = stringResource(R.string.tambah_makanan),
            onClick = onAddingMeals
        )
        Spacer(modifier = Modifier.width(10.dp))
        StyledButton(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = stringResource(R.string.selesai),
            onClick = onFinish
        )
    }
}

@Preview
@Composable
private fun BottomButtonsPreview() {
    MonitoringGinjalAppTheme {
        BottomButtons()
    }
}



@Preview(showBackground = true)
@Composable
private fun MealResultScreenPreview() {
    MonitoringGinjalAppTheme {
        MealResultScreen()
    }
}