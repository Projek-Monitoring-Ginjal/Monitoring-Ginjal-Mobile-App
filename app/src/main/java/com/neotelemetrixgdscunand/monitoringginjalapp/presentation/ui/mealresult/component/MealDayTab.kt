package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText

@Composable
fun MealDayTab(
    modifier: Modifier = Modifier,
    isSelected:Boolean = false,
    onClick:() -> Unit = { },
    text:String = "",
) {
    HeadingText(
        modifier = modifier
            .background(
                color = if(isSelected) Green20 else Color.Transparent,
                shape = RoundedCornerShape(size = 16.dp),
            )
            .border(width = 1.5.dp, color = Green20, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 1.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp)

            .clickable(onClick = onClick),
        fontSize = 14.sp,
        color = if(isSelected) Grey40 else Green20,
        text = text,
        fontWeight = FontWeight.Medium,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun MealDayTabPreview() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val tabs = remember {
        listOf(
            "Hari 1",
            "Hari 2",
            "Hari 3"
        )
    }

    MonitoringGinjalAppTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
            tabs.forEachIndexed { index, tabText ->
                MealDayTab(
                    isSelected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = tabText
                )
            }
        }
    }
}