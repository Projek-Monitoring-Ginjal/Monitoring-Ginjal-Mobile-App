package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMenu(
    modifier: Modifier = Modifier,
    iconResId:Int,
    title:String,
    onClick: () -> Unit
) {
    val cardSize = 102.dp
    val titleMaxWidth = cardSize - 4.dp


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ElevatedCard(
            modifier = Modifier.border(
                width = 1.dp,
                color = Green20,
                shape = RoundedCornerShape(10.dp)
            ),
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(cardSize)
                    .padding(12.dp),
                model = iconResId,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HeadingText(
            modifier = Modifier
                .widthIn(max = titleMaxWidth)
                .align(Alignment.CenterHorizontally),
            text = title,
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.8).sp,
            lineHeight = 14.sp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun HomeMenuPreview() {
    MonitoringGinjalAppTheme {
        HomeMenu(
            iconResId = R.drawable.ic_kidney,
            title = "Gagal Ginjaldddddddddkkddddddd",
            onClick = {}
        )
    }
}