package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.MenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getDietMenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getFluidNeedMenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getGinjalMenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getHemodialisaMenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getHemodyalisisVascularAccesCareMenuInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.component.InfoDialogGinjal
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.component.ListMenu
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ListMenuInfoGinjalScreen(routeType: String) {
    val context = LocalContext.current
    val menuItems = when (routeType) {
        "Ginjal" -> listOf(
            context.getString(R.string.pengertian_gagal_ginjal_title),
            context.getString(R.string.fungsi_ginjal_normal_title),
            context.getString(R.string.penyebab_gagal_ginjal_title),
            context.getString(R.string.tanda_gejala_title),
            context.getString(R.string.pemeriksaan_diagnosa_title),
            context.getString(R.string.pencegahan_title),
            context.getString(R.string.pengobatan_title),
            context.getString(R.string.kondisi_lanjut_title)
        )
        "Hemodialisa" -> listOf(
            context.getString(R.string.pengertian_hemodialisa_title),
            context.getString(R.string.tujuan_hemodialisa_title),
            context.getString(R.string.kapan_melakukan_hemodialisa_title),
            context.getString(R.string.bagaimana_melakukan_hemodialisa_title),
            context.getString(R.string.efek_samping_hemodialisa_title),
            context.getString(R.string.dampak_telat_cuci_darah_title),
            context.getString(R.string.di_mana_melakukan_hemodialisa_title)
        )
        "Cairan" -> listOf(
            context.getString(R.string.kenapa_cairan_dan_minuman_diaturr_title),
            context.getString(R.string.berapa_kebutuhan_cairan_title),
            context.getString(R.string.tips_mengurangi_cairan_title),
            context.getString(R.string.tips_mengontrol_haus_title),
            context.getString(R.string.pembagian_waktu_minum_title),
        )
        "Perawatan" -> listOf(
            context.getString(R.string.perawatan_akses_vaskuler_title),
            context.getString(R.string.perawatan_cimino_title)
        )
        "Diet" -> listOf(
            context.getString(R.string.kenapa_diet_diatur_title),
            context.getString(R.string.tujuan_diet_diatur_title),
            context.getString(R.string.tujuan_diet_hemodialisa_title),
            context.getString(R.string.syarat_diet_hemodialisa_title),
            context.getString(R.string.bahan_makanan_dibatasi_title),
            context.getString(R.string.tips_penggunaan_garam_title),
            context.getString(R.string.cara_mengatur_diet_title),
            context.getString(R.string.cara_mempersiapkan_makanan_title)
        )
        else -> emptyList()
    }

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
        val menuInfo = when (routeType) {
            "Ginjal" -> getGinjalMenuInfo(context, it)
            "Hemodialisa" -> {
                val hemodialisaInfo = getHemodialisaMenuInfo(context, it)
                MenuInfo(
                    title = hemodialisaInfo.title,
                    description = hemodialisaInfo.description,
                    bulletPoints = hemodialisaInfo.bulletPoints,
                    imageResId = hemodialisaInfo.imageResId
                )
            }
            "Cairan" -> {
                val fluidNeedInfo = getFluidNeedMenuInfo(context, it)
                MenuInfo(
                    title = fluidNeedInfo.title,
                    description = fluidNeedInfo.description,
                    bulletPoints = fluidNeedInfo.bulletPoints,
                    imageResId = fluidNeedInfo.imageResId
                )
            }
            "Perawatan" -> {
                val careInfo = getHemodyalisisVascularAccesCareMenuInfo(context, it)
                MenuInfo(
                    title = careInfo.title,
                    description = careInfo.description,
                    bulletPoints = careInfo.bulletPoints,
                    imageResId = careInfo.imageResId
                )
            }
            "Diet" -> {
                val dietInfo = getDietMenuInfo(context, it)
                MenuInfo(
                    title = dietInfo.title,
                    description = dietInfo.description,
                    bulletPoints = dietInfo.bulletPoints,
                    imageResId = dietInfo.imageResId
                )
            }
            else -> null
        }

        menuInfo?.let {
            InfoDialogGinjal(
                title = it.title,
                description = it.description,
                imageResId = it.imageResId,
                bulletPoints = it.bulletPoints,
                onDismiss = { selectedMenuItem = null }
            )
        }
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
    ListMenuInfoGinjalScreen("Cairan")
}
