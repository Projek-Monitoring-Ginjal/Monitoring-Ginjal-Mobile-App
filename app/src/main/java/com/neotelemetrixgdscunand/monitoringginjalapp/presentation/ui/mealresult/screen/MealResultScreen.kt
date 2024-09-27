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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.util.ListFoodnDrinkUtil
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.MealDayTab
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.NutrientPreviewCard
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component.NutritionStatBar
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.viewmodel.MealResultViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent

@Composable
fun MealResultScreen(
    modifier: Modifier = Modifier,
    viewModel: MealResultViewModel = hiltViewModel(),
    onAddMeals: (DayOptions) -> Unit = { },
    onFinish: () -> Unit = { }
) {

    val dailyNutrientNeedsInfo = viewModel.dailyNutrientNeedsInfo
    val nutritionTotalInfo = remember(dailyNutrientNeedsInfo){
        ListFoodnDrinkUtil.sumFoodNutritions(dailyNutrientNeedsInfo.meals)
    }
    val nutritionToProgressFraction = remember(nutritionTotalInfo, dailyNutrientNeedsInfo) {
        dailyNutrientNeedsInfo.dailyNutrientNeedsThreshold.run {
            listOf(
                nutritionTotalInfo.calorie to MealResultUtil.calculateProgressFraction(
                    nutritionTotalInfo.calorie.amount,
                    caloriesThreshold
                ),
                nutritionTotalInfo.fluid to MealResultUtil.calculateProgressFraction(
                    nutritionTotalInfo.fluid.amount,
                    fluidThreshold
                ),
                nutritionTotalInfo.protein to MealResultUtil.calculateProgressFraction(
                    nutritionTotalInfo.protein.amount,
                    proteinThreshold
                ),
                nutritionTotalInfo.natrium to MealResultUtil.calculateProgressFraction(
                    nutritionTotalInfo.natrium.amount,
                    natriumThreshold
                ),
                nutritionTotalInfo.kalium to MealResultUtil.calculateProgressFraction(
                    nutritionTotalInfo.kalium.amount,
                    kaliumThreshold
                )
            )
        }
    }

    val context = LocalContext.current

    var selectedTabIndex by remember {
        mutableIntStateOf(viewModel.dayOptions.index)
    }

    //val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

    val tabs = remember {
        listOf(
            R.string.hari_1,
            R.string.hari_2,
            R.string.hari_3,
            R.string.hari_4,
        )
    }


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

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 24.dp, top = 20.dp),
            modifier = Modifier.weight(1f)
        ) {

            item{
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                    tabs.forEachIndexed { index, tabTextResId ->
                        MealDayTab(
                            isSelected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index
                                viewModel.dayOptions = DayOptions.entries[selectedTabIndex]
                            },
                            text = stringResource(id = tabTextResId)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                HeadingText(
                    text = stringResource(R.string.hasil_makan_hari_ini),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            itemsIndexed(nutritionToProgressFraction){index, item ->

                val (nutrient, progressFraction) = item
                val backgroundColor = ListFoodnDrinkUtil.getColorFromGradient(progressFraction)

                NutritionStatBar(
                    backgroundColor = backgroundColor,
                    nutritionalContentName = nutrient.name.getValue(context),
                    nutritionalContentValue = nutrient.amount,
                    nutritionalContentUnit = nutrient.unit.getValue(context)
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
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        BottomButtons(
            onAddingMeals = {
                onAddMeals(viewModel.dayOptions)
            },
            onFinish = onFinish
        )
    }

}

@Composable
private fun BottomButtons(
    onAddingMeals:() -> Unit = {},
    onFinish:()->Unit = {}
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
            text = stringResource(id = R.string.simpan),
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