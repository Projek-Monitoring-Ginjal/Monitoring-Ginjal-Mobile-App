package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import android.content.Context
import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class FluidNeedMenuInfo(
    val title: String,
    val description: String,
    val bulletPoints: List<String> = emptyList(),
    val imageResId: Int
)

fun getFluidNeedMenuInfo(context: Context, title: String): FluidNeedMenuInfo {
    return when (title) {
        context.getString(R.string.kenapa_cairan_dan_minuman_diaturr_title) -> FluidNeedMenuInfo(
            title = context.getString(R.string.kenapa_cairan_dan_minuman_diaturr_title),
            description = context.getString(R.string.kenapa_cairan_dan_minuman_diaturr_desc),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.berapa_kebutuhan_cairan_title) -> FluidNeedMenuInfo(
            title = context.getString(R.string.berapa_kebutuhan_cairan_title),
            description = context.getString(R.string.berapa_kebutuhan_cairan_title),
            bulletPoints = context.resources.getStringArray(R.array.berapa_kebutuhan_cairan_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.tips_mengurangi_cairan_title) -> FluidNeedMenuInfo(
            title = context.getString(R.string.tips_mengurangi_cairan_title),
            description = context.getString(R.string.tips_mengurangi_cairan_title),
            bulletPoints = context.resources.getStringArray(R.array.tips_mengurangi_cairan_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.tips_mengontrol_haus_title) -> FluidNeedMenuInfo(
            title = context.getString(R.string.tips_mengontrol_haus_title),
            description = context.getString(R.string.tips_mengontrol_haus_title),
            bulletPoints = context.resources.getStringArray(R.array.tips_mengontrol_haus_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.pembagian_waktu_minum_title) -> FluidNeedMenuInfo(
            title = context.getString(R.string.pembagian_waktu_minum_title),
            description = context.getString(R.string.pembagian_waktu_minum_title),
            bulletPoints = context.resources.getStringArray(R.array.pembagian_waktu_minum_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        else -> FluidNeedMenuInfo(
            title = context.getString(R.string.informasi_tidak_tersedia),
            description = context.getString(R.string.informasi_tidak_tersedia),
            imageResId = R.drawable.doctor_image
        )
    }
}
