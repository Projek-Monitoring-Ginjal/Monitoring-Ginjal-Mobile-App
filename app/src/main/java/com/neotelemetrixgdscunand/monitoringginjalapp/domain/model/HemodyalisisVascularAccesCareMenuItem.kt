package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import android.content.Context
import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class HemodyalisisVascularAccesCareMenuInfo(
    val title: String,
    val description: String,
    val bulletPoints: List<String> = emptyList(),
    val imageResId: Int
)

fun getHemodyalisisVascularAccesCareMenuInfo(context: Context, title: String): HemodyalisisVascularAccesCareMenuInfo {
    return when (title) {
        context.getString(R.string.perawatan_akses_vaskuler_title) -> HemodyalisisVascularAccesCareMenuInfo(
            title = context.getString(R.string.perawatan_akses_vaskuler_title),
            description = context.getString(R.string.perawatan_akses_vaskuler_title),
            bulletPoints = context.resources.getStringArray(R.array.perawatan_akses_vaskuler_bullet_points).toList(),
            imageResId = R.drawable.perawatan_vaskular
        )
        context.getString(R.string.perawatan_cimino_title) -> HemodyalisisVascularAccesCareMenuInfo(
            title = context.getString(R.string.perawatan_cimino_title),
            description = context.getString(R.string.perawatan_cimino_title),
            bulletPoints = context.resources.getStringArray(R.array.perawatan_cimino_bullet_points).toList(),
            imageResId = R.drawable.perawatan_cimino
        )
        else -> HemodyalisisVascularAccesCareMenuInfo(
            title = context.getString(R.string.informasi_tidak_tersedia),
            description = context.getString(R.string.informasi_tidak_tersedia),
            imageResId = R.drawable.doctor_image
        )
    }
}
