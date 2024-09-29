package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.screen

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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HomeMenuItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.ComposableRiveAnimationView
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.HomeMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import kotlinx.coroutines.delay

@Composable
fun HomeMenuScreen(
    modifier: Modifier = Modifier,
    onMenuItemClick: (Route) -> Unit = { }
) {

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
            // Trigger item visibility one by one
            menuItems.indices.forEach { index ->
                delay(1000L) // 1 second delay for each menu item
                visibleMenuIndex = index
            }
        }
    }

    Surface(color = Grey40) {
        // Removed unnecessary padding here
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .padding(16.dp), // Padding is still applied to content
                horizontalAlignment = if (!isOpeningAnimationComplete) Alignment.CenterHorizontally else Alignment.Start,
                verticalArrangement = if (!isOpeningAnimationComplete) Arrangement.Center else Arrangement.Top
            ) {

                HeadingText(
                    text = stringResource(R.string.apa_yang_bapak_ibu_ingin_ketahui),
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )

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
                                    onMenuItemClick(menuItem.route)
                                }
                            )
                        }
                    }
                }
            }

            // Ensure the animation is fixed at the bottom-left corner without padding
            ComposableRiveAnimationView(
                animation = R.raw.animasiawal4,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 4.dp)
                    .size(250.dp) // The size is applied but without padding
            )

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

