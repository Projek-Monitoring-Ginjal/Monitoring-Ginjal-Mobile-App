package com.neotelemetrixgdscunand.monitoringginjalapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.component.MultiColorText
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.screen.HomeScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.screen.LoginScreen
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Yellow20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){

    var isSignedIn by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            if(isSignedIn){
                TopBarApp()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if(isSignedIn) Home else Login,
            modifier = Modifier.padding(innerPadding)
        ){
            composable<Login> {
                LoginScreen(
                    onLoginClick = {
                        isSignedIn = true
                    }
                )
            }
            composable<Home> {
                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                spotColor = Color.DarkGray
            ),
        title = {
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
    MonitoringGinjalAppTheme {
        TopBarApp()
    }
}