package com.neotelemetrixgdscunand.monitoringginjalapp.page

import BottomBarMakan
import KaloriList
import ProteinList
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.components.TopBarMandeh
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.AppText
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Typography

@Composable
fun MakanSayaPage(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxSize()) {
        TopBarMandeh()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            state = listState
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = AppText.makanSayaTitle,
                        style = Typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = AppText.pilihMakananHarian,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }

            item {
                Text(
                    text = AppText.kaloriTitle,
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                KaloriList()
            }

            item {
                Text(
                    text = AppText.proteinTitle,
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                ProteinList()
            }
        }

        BottomBarMakan()
    }
}

@Preview
@Composable
private fun MakanSayaPreview() {
    MakanSayaPage()
}
