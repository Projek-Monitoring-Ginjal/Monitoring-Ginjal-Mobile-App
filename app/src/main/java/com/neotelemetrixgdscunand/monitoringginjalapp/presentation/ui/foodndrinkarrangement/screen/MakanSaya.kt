package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.foodndrinkarrangement.screen

import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.foodndrinkarrangement.component.BottomBarMakan
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.foodndrinkarrangement.component.KaloriList
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.foodndrinkarrangement.component.ProteinList
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component.TopBarMandeh
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Typography

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
                        text = stringResource(R.string.makan_saya_hari_ini),
                        style = Typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.pilih_makanan_harian),
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }

            item {
                Text(
                    text = stringResource(R.string.kalori),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                KaloriList()
            }

            item {
                Text(
                    text = stringResource(R.string.protein),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
                ProteinList()
            }
        }

        BottomBarMakan()
    }
}

@Preview(showBackground = true)
@Composable
private fun MakanSayaPreview() {
    MakanSayaPage()
}
