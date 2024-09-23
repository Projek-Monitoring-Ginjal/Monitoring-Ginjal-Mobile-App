package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component.HomeMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText

@Composable
fun HomeMenuScreen(
    modifier: Modifier = Modifier,
    onMenuItemClick:(Route) -> Unit = { }
) {
    Surface(color = Grey40) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
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
                items(items = menuItems, key = { it.hashCode() }){ menuItem ->
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
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MonitoringGinjalAppTheme {
        HomeMenuScreen()
    }
}