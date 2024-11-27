package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.screen

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HomeMenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.DailyNutrientCalc
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.InformationMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.MealResult
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.ConfirmationDialog
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.StartHemodialisaDialog
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.viewmodel.HomeMenuViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.component.ComposableRiveAnimationView
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.component.MenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import kotlinx.coroutines.delay

@Composable
fun HomeMenuScreen(
    modifier: Modifier = Modifier,
    onMenuItemClick: (Route) -> Unit = { },
    viewModel: HomeMenuViewModel = hiltViewModel(),
    onLogout: () -> Unit = { }
) {
    val context = LocalContext.current // Access the current context

    var isNavigatingOut by remember {
        mutableStateOf(false)
    }

    val mediaPlayer = remember{
        MediaPlayer.create(context, R.raw.soundutama)
    }
    var isVisible by remember {
        mutableStateOf(true)
    }

    var visibleMenuIndex by remember {
        mutableIntStateOf(-1) // Start with -1, so no items are visible initially
    }

    val menuItems = remember {
        HomeMenuItem.entries
    }

    LaunchedEffect(key1 = isNavigatingOut) {
        if(!isNavigatingOut){
            isVisible = true
            menuItems.indices.forEach { index ->
                delay(1000L) // 1 second delay for each menu item
                visibleMenuIndex = index
            }
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
                is UIEvent.NavigateToExistingHemodialisaPeriod -> {
                    val route = MealResult(
                        dayOptions = DayOptions.FirstDay,
                        hemodialisaType = it.hemodialisaType
                    )
                    onMenuItemClick(route)
                    isNavigatingOut = true
                }
                is UIEvent.UserLogout ->{
                    onLogout()
                    isNavigatingOut = true
                }
                else -> {}
            }
        }
    }

    HomeMenuContent(
        modifier = modifier,
        isLoading = viewModel.isLoading,
        menuItems = menuItems,
        visibleMenuIndex = visibleMenuIndex,
        onMenuItemClick = onMenuItemClick,
        navigatingOut = {
            isNavigatingOut = true
        },
        checkIsInPeriods = viewModel::checkIsInPeriods,
        showLogoutDialog = viewModel::onShowDialog,
        isDialogLogoutShown = viewModel.showDialog,
        isDialogStartHemodialisaShown = viewModel.showStartHemodialisaDialog,
        onDismissDialogLogout = viewModel::onDismissDialog,
        onDismissStartHemodialisaDialog = viewModel::onDismissStartHemodialisaDialog,
        onLogout = viewModel::logout,
        onNavigateToBodyWeightCalc = { hemodialisaType ->
            val route = DailyNutrientCalc(hemodialisaType)
            onMenuItemClick(route)
            isNavigatingOut = true
        }
    )
}


@Composable
fun HomeMenuContent(
    modifier: Modifier = Modifier,
    isLoading:Boolean = false,
    menuItems:List<HomeMenuItem> = emptyList(),
    visibleMenuIndex:Int = 0,
    onMenuItemClick: (Route) -> Unit = { },
    navigatingOut:() -> Unit = {},
    checkIsInPeriods:() -> Unit = {},
    showLogoutDialog:() -> Unit = {},
    isDialogLogoutShown:Boolean = false,
    isDialogStartHemodialisaShown:Boolean = false,
    onDismissDialogLogout:() -> Unit = {},
    onDismissStartHemodialisaDialog:()->Unit = {},
    onLogout:() -> Unit = {},
    onNavigateToBodyWeightCalc:(HemodialisaType)-> Unit = {}
) {

    Surface(color = Grey40) {
        // Removed unnecessary padding here
        Box(modifier = Modifier.fillMaxSize()) {

            if(isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Green20
                )
            }else{
                BoxWithConstraints(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) {

                    Spacer(modifier = Modifier.height(16.dp))

                    ComposableRiveAnimationView(
                        animation = R.raw.utama,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(
                                height = this@BoxWithConstraints.maxHeight * 0.75f,
                                width = this@BoxWithConstraints.maxWidth
                            ) // The size is applied but without padding
                    )

                    // Make the LazyVerticalGrid scrollable if it becomes too long
                    LazyColumn(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        //modifier = Modifier.weight(1f), // This ensures the grid takes up remaining space and is scrollable
                        contentPadding = PaddingValues(top = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(
                            items = menuItems,
                            key = { _, it -> it.hashCode() }
                        ) { i, menuItem ->

                            AnimatedVisibility(
                                visible = i <= visibleMenuIndex, // Only show items up to the current index
                                enter = scaleIn(),
                                exit = scaleOut()
                            ) {
                                MenuItem(
                                    iconResId = menuItem.iconResId,
                                    title = stringResource(id = menuItem.titleTextResId),
                                    onClick = {
                                        if(menuItem != HomeMenuItem.FoodArrangment){
                                            onMenuItemClick(InformationMenu)
                                            navigatingOut()
                                        }else{
                                            checkIsInPeriods()
                                        }
                                    }
                                )
                            }
                        }
                    }

                    StyledButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        text = stringResource(R.string.keluar),
                        onClick = showLogoutDialog
                    )
                }
            }

            StartHemodialisaDialog(
                isDialogShown = isDialogStartHemodialisaShown,
                onDismiss = onDismissStartHemodialisaDialog,
                onNavigateToStartHemodialisa = onNavigateToBodyWeightCalc
            )

            ConfirmationDialog(
                onDismiss = onDismissDialogLogout,
                onConfirm = onLogout,
                isShown = isDialogLogoutShown,
                text = stringResource(R.string.keluar_dari_aplikasi)
            )
        }
    }
}








@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeMenuScreenPreview() {
    MonitoringGinjalAppTheme {
        HomeMenuContent()
    }
}

