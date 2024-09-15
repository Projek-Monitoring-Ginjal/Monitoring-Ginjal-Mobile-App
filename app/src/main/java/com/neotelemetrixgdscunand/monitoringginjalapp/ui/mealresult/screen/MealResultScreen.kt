package com.neotelemetrixgdscunand.monitoringginjalapp.ui.mealresult.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.model.getNutrientContents
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.login.component.StyledButton
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.mealresult.component.MealDayTab
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.mealresult.component.NutrientPreviewCard
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.mealresult.component.NutritionStatBar
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Green40
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Yellow40

@Composable
fun MealResultScreen(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    //val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

    val tabs = remember {
        listOf(
            "Hari 1",
            "Hari 2",
            "Hari 3"
        )
    }

    val nutrientContents = remember { getNutrientContents() }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp),

    ) {

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 24.dp, top = 20.dp),
            modifier = Modifier.weight(1f)
        ) {

            item{
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                    tabs.forEachIndexed { index, tabText ->
                        MealDayTab(
                            isSelected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = tabText
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                HeadingText(
                    text = stringResource(R.string.hasil_makan_hari_ini),
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            itemsIndexed(nutrientContents){index, item ->
                NutritionStatBar(
                    color = if(index % 4 == 0) Yellow40 else Green40,
                    nutritionalContentName = item.name,
                    nutritionalContentValue = item.value,
                    nutritionalContentUnit = item.unit
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(nutrientContents){
                NutrientPreviewCard(nutrientContentName = it.name)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        BottomButtons()
    }

}

@Composable
private fun BottomButtons(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(top = 20.dp, bottom = 16.dp)
    ) {
        StyledButton(
            modifier = Modifier
                .weight(1f)
                .border(width = 2.dp, color = Green20, shape = RoundedCornerShape(16.dp)),
            backgroundColor = Color.White,
            textColor = Green20,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = stringResource(R.string.tambah_makanan),
        )
        Spacer(modifier = Modifier.width(10.dp))
        StyledButton(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = stringResource(id = R.string.simpan),

        )
    }
}

@Preview
@Composable
private fun BottomButtonsPreview() {
    MonitoringGinjalAppTheme {
        BottomButtons()
    }
}



@Preview(showBackground = true)
@Composable
private fun MealResultScreenPreview() {
    MonitoringGinjalAppTheme {
        MealResultScreen()
    }
}