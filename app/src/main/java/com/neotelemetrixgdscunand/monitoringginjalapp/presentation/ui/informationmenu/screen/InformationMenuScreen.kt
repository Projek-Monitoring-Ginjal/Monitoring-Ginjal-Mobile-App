package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu.screen

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
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

    var mediaPlayer = remember{
        MediaPlayer.create(context, R.raw.bahasa)
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

    // Animasi penampilan menu
    LaunchedEffect(key1 = isNavigatingOut) {
        if(!isNavigatingOut){
            isVisible = true
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release() // Release the media player when done
            }

            // Trigger item visibility one by one
            delay(1000L) // 1 second delay for each menu item
            menuItems.indices.forEach { index ->
                delay(2000L) // 1 second delay for each menu item
                visibleMenuIndex = index
            }
        }
                else if(mediaPlayer != null){
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

    // Menerima event UI dari ViewModel
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
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
        Box(modifier = Modifier.fillMaxSize()) {
            // Cek apakah tombol "Skip" perlu ditampilkan
            if (visibleMenuIndex < menuItems.lastIndex) {
                AnimatedVisibility(
                    visible = visibleMenuIndex < menuItems.lastIndex, // Hanya tampil jika belum semua menu ditampilkan
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    Text(
                        text = stringResource(R.string.skip),
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .clickable {
                                mediaPlayer?.stop()
                                mediaPlayer?.release()
                                mediaPlayer = null
                                isNavigatingOut = true // Trigger exit navigation
                                isVisible = false // Hide the grid animation
                                visibleMenuIndex =
                                    menuItems.size - 1 // Show all menu items instantly
                            }
                    )
                }
            }

            Column(
                modifier = modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // LazyVerticalGrid untuk menampilkan menu secara scrollable
                LazyVerticalGrid(
                    modifier = Modifier.weight(1f), // Grid yang dapat discroll
                    contentPadding = PaddingValues(top = 4.dp),
                    columns = GridCells.Fixed(2), // Set ke 2 kolom
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(
                        items = menuItems,
                        key = { _, it -> it.hashCode() }
                    ) { i, menuItem ->

                        AnimatedVisibility(
                            visible = i <= visibleMenuIndex, // Tampilkan item hingga indeks saat ini
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
                // Komponen animasi di bagian bawah layar
                ComposableRiveAnimationView(
                    animation = R.raw.animasiawal,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 36.dp)
                        .size(220.dp)
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
