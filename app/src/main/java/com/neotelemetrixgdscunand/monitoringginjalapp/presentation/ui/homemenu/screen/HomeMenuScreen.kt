package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HomeMenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.DailyNutrientCalc
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.MealResult
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.HomeMenuViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.ComposableRiveAnimationView
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.HomeMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel.ListFoodnDrinkViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import kotlinx.coroutines.delay

@Composable
fun HomeMenuScreen(
    modifier: Modifier = Modifier,
    onMenuItemClick: (Route) -> Unit = { },
    viewModel: HomeMenuViewModel = hiltViewModel()
) {

    var isOpeningAnimationComplete by remember {
        mutableStateOf(false)
    }

    val animationDuration = 5000L
    var isVisible by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = isOpeningAnimationComplete) {
        if (!isOpeningAnimationComplete) {
            delay(animationDuration)
            isOpeningAnimationComplete = true
        }else {
            isVisible = true
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{
            when(it){
                is UIEvent.ShowToast -> Toast.makeText(
                    context,
                    it.message.getValue(context),
                    Toast.LENGTH_SHORT
                ).show()
                is UIEvent.DetermineNextScreenHemodialisaCheck -> {
                    val route:Route = if(it.isInPeriods){
                        MealResult(dayOptions = DayOptions.FirstDay)
                    }else DailyNutrientCalc

                    onMenuItemClick(route)
                }
                else -> {}
            }
        }
    }

    Surface(color = Grey40) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = if (!isOpeningAnimationComplete) Alignment.CenterHorizontally else Alignment.Start,
            verticalArrangement = if (!isOpeningAnimationComplete) Arrangement.Center else Arrangement.Top
        ) {

            if (!isOpeningAnimationComplete) {
                ComposableRiveAnimationView(animation = R.raw.animasi_berpikir)
            } else {
                val menuItems = remember {
                    HomeMenuItem.entries
                }

                HeadingText(
                    text = stringResource(R.string.apa_yang_bapak_ibu_ingin_ketahui),
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    contentPadding = PaddingValues(top = 4.dp),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    itemsIndexed(
                        items = menuItems,
                        key = { _, it -> it.hashCode() }
                    ) { i, menuItem ->

                        AnimatedVisibility(
                            visible = isVisible,
                            enter = scaleIn(),
                            exit = scaleOut()
                        ) {
                            HomeMenu(
                                iconResId = menuItem.iconResId,
                                title = stringResource(id = menuItem.titleTextResId),
                                onClick = {
                                    if(menuItem != HomeMenuItem.FoodArrangment){
                                        onMenuItemClick(menuItem.route)
                                    }else{
                                        viewModel.checkIsInPeriods()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }


}



@Composable
fun LogoutDialog(
    viewModel: ListFoodnDrinkViewModel,
    onDismiss: () -> Unit
) {
    TODO()
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            /*PortionOption(onOptionSelected = { portion ->
                viewModel.addFoodItem(portion, foodItem)
                onDismiss()
            })*/
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MonitoringGinjalAppTheme {
        HomeMenuScreen()
    }
}