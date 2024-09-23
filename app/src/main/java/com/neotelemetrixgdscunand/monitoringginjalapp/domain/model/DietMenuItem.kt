package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import android.content.Context
import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class DietMenuInfo(
    val title: String,
    val description: String,
    val bulletPoints: List<String> = emptyList(),
    val imageResId: Int
)

fun getDietMenuInfo(context: Context, title: String): DietMenuInfo {
    return when (title) {
        context.getString(R.string.kenapa_diet_diatur_title) -> DietMenuInfo(
            title = context.getString(R.string.kenapa_diet_diatur_title),
            description = context.getString(R.string.kenapa_diet_diatur_description),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.tujuan_diet_diatur_title) -> DietMenuInfo(
            title = context.getString(R.string.tujuan_diet_diatur_title),
            description = context.getString(R.string.tujuan_diet_diatur_description),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.tujuan_diet_hemodialisa_title) -> DietMenuInfo(
            title = context.getString(R.string.tujuan_diet_hemodialisa_title),
            description = context.getString(R.string.tujuan_diet_hemodialisa_title),
            bulletPoints = context.resources.getStringArray(R.array.tujuan_diet_hemodialisa_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.syarat_diet_hemodialisa_title) -> DietMenuInfo(
            title = context.getString(R.string.syarat_diet_hemodialisa_title),
            description = context.getString(R.string.syarat_diet_hemodialisa_title),
            bulletPoints = context.resources.getStringArray(R.array.syarat_diet_hemodialisa_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.bahan_makanan_dibatasi_title) -> DietMenuInfo(
            title = context.getString(R.string.bahan_makanan_dibatasi_title),
            description = context.getString(R.string.bahan_makanan_dibatasi_title),
            bulletPoints = context.resources.getStringArray(R.array.bahan_makanan_dibatasi_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.tips_penggunaan_garam_title) -> DietMenuInfo(
            title = context.getString(R.string.tips_penggunaan_garam_title),
            description = context.getString(R.string.tips_penggunaan_garam_title),
            bulletPoints = context.resources.getStringArray(R.array.tips_penggunaan_garam_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.cara_mengatur_diet_title) -> DietMenuInfo(
            title = context.getString(R.string.cara_mengatur_diet_title),
            description = context.getString(R.string.cara_mengatur_diet_title),
            bulletPoints = context.resources.getStringArray(R.array.cara_mengatur_diet_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.cara_mempersiapkan_makanan_title) -> DietMenuInfo(
            title = context.getString(R.string.cara_mempersiapkan_makanan_title),
            description = context.getString(R.string.cara_mempersiapkan_makanan_title),
            bulletPoints = context.resources.getStringArray(R.array.cara_mempersiapkan_makanan_bullet_points).toList(),
            imageResId = R.drawable.doctor_image
        )
        else -> DietMenuInfo(
            title = context.getString(R.string.informasi_tidak_tersedia),
            description = context.getString(R.string.informasi_tidak_tersedia),
            imageResId = R.drawable.doctor_image
        )
    }
}
