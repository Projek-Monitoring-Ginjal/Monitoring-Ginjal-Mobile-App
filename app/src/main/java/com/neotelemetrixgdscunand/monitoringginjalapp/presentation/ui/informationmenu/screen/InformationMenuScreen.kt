package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.screen

import android.media.MediaPlayer
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.InformationMenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.InformationMenuViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.component.ComposableRiveAnimationView
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.component.MenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import kotlinx.coroutines.delay

@Composable
fun InformationMenuScreen(
    modifier: Modifier = Modifier,
    onMenuItemClick: (Route) -> Unit = { },
    viewModel: InformationMenuViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = { }
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
        InformationMenuItem.entries
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
                else -> {}
            }
        }
    }



    Surface(color = Grey40) {
        // Removed unnecessary padding here
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
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
                            MenuItem(
                                iconResId = menuItem.iconResId,
                                title = stringResource(id = menuItem.titleTextResId),
                                onClick = {
                                    onMenuItemClick(menuItem.route)
                                    isNavigatingOut = true
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
                Spacer(modifier = Modifier.height(8.dp))
                StyledButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, start = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.kembali),
                    onClick = {
                        onNavigateBack()
                        isNavigatingOut = true
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InformationMenuScreenPreview() {
    MonitoringGinjalAppTheme {
        InformationMenuScreen()
    }
}

