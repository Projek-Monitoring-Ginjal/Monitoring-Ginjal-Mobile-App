package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.screen

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HomeMenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.DailyNutrientCalc
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.MealResult
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.HomeMenuViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.ComposableRiveAnimationView
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.HomeMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
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

    var isOpeningAnimationComplete by remember {
        mutableStateOf(true)
    }

    val animationDuration = 5000L
    var isVisible by remember {
        mutableStateOf(true)
    }

    var visibleMenuIndex by remember {
        mutableStateOf(-1) // Start with -1, so no items are visible initially
    }

    val menuItems = remember {
        HomeMenuItem.entries
    }

    LaunchedEffect(key1 = isOpeningAnimationComplete) {
        if (!isOpeningAnimationComplete) {
            delay(animationDuration)
            isOpeningAnimationComplete = true
        } else {
            isVisible = true
            val mediaPlayer = MediaPlayer.create(context, R.raw.soundutama) // Replace with your audio file
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release() // Release the media player when done
            }
            // Trigger item visibility one by one
            delay(2000L) // 1 second delay for each menu item
            menuItems.indices.forEach { index ->
                delay(3000L) // 1 second delay for each menu item
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
                is UIEvent.DetermineNextScreenHemodialisaCheck -> {
                    val route:Route = if(it.isInPeriods){
                        MealResult(dayOptions = DayOptions.FirstDay)
                    }else DailyNutrientCalc

                    onMenuItemClick(route)
                }
                is UIEvent.UserLogout ->{
                    onLogout()
                }
                else -> {}
            }
        }
    }



    Surface(color = Grey40) {
        // Removed unnecessary padding here
        Box(modifier = Modifier.fillMaxSize()) {

            if(viewModel.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Green20
                )
            }else{
                Column(
                    modifier = modifier
                        .padding(16.dp), // Padding is still applied to content
                    horizontalAlignment = if (!isOpeningAnimationComplete) Alignment.CenterHorizontally else Alignment.Start,
                    verticalArrangement = if (!isOpeningAnimationComplete) Arrangement.Center else Arrangement.Top
                ) {



                    Spacer(modifier = Modifier.height(16.dp))

                    // Make the LazyVerticalGrid scrollable if it becomes too long
                    LazyVerticalGrid(
                        modifier = Modifier.weight(1f), // This ensures the grid takes up remaining space and is scrollable
                        contentPadding = PaddingValues(top = 4.dp),
                        columns = GridCells.Fixed(2), // Set to 2 columns
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

                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    // Ensure the animation is fixed at the bottom-left corner without padding
                    ComposableRiveAnimationView(
                        animation = R.raw.animasiawal,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 36.dp)
                            .size(220.dp) // The size is applied but without padding
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    StyledButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp, start = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.keluar),
                        onClick = viewModel::onShowDialog
                    )
                }

            }

            if(viewModel.showDialog){
                LogoutDialog(
                    onDismiss = viewModel::onDismissDialog,
                    onLogout = viewModel::logout
                )
            }
        }
    }
}



@Composable
fun LogoutDialog(
    onDismiss: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 48.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column{
                HeadingText(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.keluar_dari_aplikasi),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    StyledButton(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                width = 2.dp,
                                color = Green20,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        backgroundColor = Color.White,
                        textColor = Green20,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        text = stringResource(id = R.string.kembali),
                        onClick = onDismiss
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    StyledButton(
                        modifier = Modifier
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        text = stringResource(R.string.yes),
                        onClick = onLogout
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LogoutDialogPreview() {
    MonitoringGinjalAppTheme {
        LogoutDialog(){

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

