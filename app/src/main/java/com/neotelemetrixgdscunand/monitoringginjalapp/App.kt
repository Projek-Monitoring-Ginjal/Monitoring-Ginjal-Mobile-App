package com.neotelemetrixgdscunand.monitoringginjalapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.DailyNutrientCalc
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.HomeMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ListFoodnDrink
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ListMenuInfoGinjal
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Login
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.MealResult
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Neutral03
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.screen.DailyNutrientsCalcScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.viewmodel.DailyNutrientCalcUtilVM
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.HomeMenuViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.screen.HomeMenuScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.screen.ListFoodnDrinkScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel.ListFoodnDrinkViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.screen.ListMenuInfoGinjalScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.MultiColorText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.screen.LoginScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.viewmodel.LoginViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.screen.MealResultScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.viewmodel.MealResultViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.navigateWithCheck

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){

    var isSignedIn by remember { mutableStateOf(false) }

    val currentBackStackEntryState = navController.currentBackStackEntryAsState()
    val currentRoute by remember{
        derivedStateOf {
            currentBackStackEntryState.value?.destination?.route
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            if(isSignedIn){
                TopBarApp(
                    onNavigateUp = {
                        navController.navigateUp()
                    },
                    route = currentRoute
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if(isSignedIn) HomeMenu else Login,
            modifier = Modifier.padding(innerPadding)
        ){
            composable<Login> {
                val viewModel:LoginViewModel = hiltViewModel()

                LoginScreen(
                    onLoginClick = {
                        isSignedIn = true
                    },
                    viewModel = viewModel
                )
            }
            composable<HomeMenu> {
                val viewModel:HomeMenuViewModel = hiltViewModel()

                HomeMenuScreen(
                    onMenuItemClick = { route ->
                        navController.navigateWithCheck(route)
                    },
                    viewModel = viewModel
                )
            }
            composable<DailyNutrientCalc> {
                val viewModel: DailyNutrientCalcUtilVM = hiltViewModel()

                DailyNutrientsCalcScreen(
                    onNavigate = {
                        navController.navigateWithCheck(
                            ListFoodnDrink(DayOptions.FirstDay)
                        )
                    },
                    viewModel = viewModel
                )
            }
            composable<ListFoodnDrink> {
                val viewModel:ListFoodnDrinkViewModel = hiltViewModel()

                ListFoodnDrinkScreen(
                    onBackClick = { /*TODO*/ },
                    onNavigateToMealResult = { dayOptions ->
                        navController.navigateWithCheck(
                            MealResult(dayOptions)
                        )
                    },
                    viewModel = viewModel
                    )
            }
            composable<MealResult> {
                val viewModel:MealResultViewModel = hiltViewModel()
                val listFoodnDrink:ListFoodnDrink = it.toRoute()

                MealResultScreen(
                    viewModel = viewModel,
                    onAddMeals = { dayOptions ->
                        navController.navigateWithCheck(
                            ListFoodnDrink(dayOptions)
                        ){
                            popUpTo(HomeMenu){
                                inclusive = false
                            }
                        }
                    },
                    onFinish = {
                        navController.navigateWithCheck(HomeMenu){
                            popUpTo(HomeMenu){
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<ListMenuInfoGinjal> {
                ListMenuInfoGinjalScreen(routeType = it.arguments?.getString("routeType") ?: "")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    route:String?
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Neutral03
        ),
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                spotColor = Color.DarkGray
            ),
        navigationIcon = {
            if(route != HomeMenu::class.java.canonicalName){
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = onNavigateUp),
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        },
        title = {
            if(route == ListFoodnDrink::class.java.canonicalName){
                HeadingText(
                    text = stringResource(R.string.daftar_makanan_dan_minuman),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }else{
                MultiColorText(
                    textWithColors = arrayOf(
                        Pair(
                            "MAN",
                            Yellow20
                        ),
                        Pair(
                            "DEH",
                            Green20
                        )
                    )
                )
            }

        }
    )
}


@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    MonitoringGinjalAppTheme {
        App()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TopAppBarPreview() {
    val navController = rememberNavController()

    MonitoringGinjalAppTheme {
        TopBarApp(
            onNavigateUp = {
                navController.navigateUp()
            },
            route = null
        )
    }
}