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
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.viewmodel.HomeMenuViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.component.ComposableRiveAnimationView
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.component.MenuItem
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
        }else if(mediaPlayer != null){
            try {
                if(mediaPlayer.isPlaying && mediaPlayer.currentPosition > 0){
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
            }catch (e:IllegalStateException){
                e.printStackTrace()
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
        onDismissDialogLogout = viewModel::onDismissDialog,
        onLogout = viewModel::logout
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
    onDismissDialogLogout:() -> Unit = {},
    onLogout:() -> Unit = {}
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
                        animation = R.raw.animasiawal,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(
                                height = this@BoxWithConstraints.maxHeight * 0.45f,
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
                                            onMenuItemClick(menuItem.route)
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

            if(isDialogLogoutShown){
                LogoutDialog(
                    onDismiss = onDismissDialogLogout,
                    onLogout = onLogout
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
private fun HomeMenuScreenPreview() {
    MonitoringGinjalAppTheme {
        HomeMenuContent()
    }
}

