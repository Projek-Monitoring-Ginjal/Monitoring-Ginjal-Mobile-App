package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getGinjalMenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.component.InfoDialogGinjal
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.component.ListMenu
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ListMenuInfoGinjalScreen() {
    val context = LocalContext.current
    val menuItems = listOf(
        context.getString(R.string.pengertian_gagal_ginjal),
        context.getString(R.string.fungsi_ginjal_normal),
        context.getString(R.string.penyebab_gagal_ginjal_kronis),
        context.getString(R.string.tanda_dan_gejala),
        context.getString(R.string.pemeriksaan_untuk_diagnosa),
        context.getString(R.string.pencegahan),
        context.getString(R.string.pengobatan),
        context.getString(R.string.kondisi_lebih_lanjut_gagal_ginjal_kronis)
    )

    var selectedMenuItem by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        itemsIndexed(menuItems) { index, item ->
            AnimatedListMenu(
                title = item,
                index = index,
                onClick = { selectedMenuItem = item }
            )
        }
    }

    selectedMenuItem?.let {
        val menuInfo = getGinjalMenuInfo( context, it)
        InfoDialogGinjal(
            title = it,
            description = menuInfo.description,
            imageResId = menuInfo.imageResId,
            bulletPoints = menuInfo.bulletPoints,
            onDismiss = { selectedMenuItem = null }
        )
    }
}

@Composable
fun AnimatedListMenu(title: String, index: Int, onClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 100L)
        visible = true
    }

    val offsetX by animateFloatAsState(if (visible) 0f else 300f)
    val alpha by animateFloatAsState(if (visible) 1f else 0f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .alpha(alpha)
            .clickable { onClick() }
    ) {
        ListMenu(title = title)
    }
}

@Preview(showBackground = true)
@Composable
private fun ListMenuInfoGinjalPreview() {
    ListMenuInfoGinjalScreen()
}
