package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import android.content.Context
import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class MenuInfo(
    val title: String,
    val description: String,
    val bulletPoints: List<String> = emptyList(),
    val imageResId: Int
)

fun getGinjalMenuInfo(context: Context, title: String): MenuInfo {
    return when (title) {
        context.getString(R.string.pengertian_gagal_ginjal_title) -> MenuInfo(
            title = context.getString(R.string.pengertian_gagal_ginjal_title),
            description = context.getString(R.string.pengertian_gagal_ginjal_description),
            imageResId = R.drawable.doctor_image
        )
        context.getString(R.string.fungsi_ginjal_normal_title) -> MenuInfo(
            title = context.getString(R.string.fungsi_ginjal_normal_title),
            description = context.getString(R.string.fungsi_ginjal_normal_description),
            imageResId = R.drawable.doctor_image2
        )
        context.getString(R.string.penyebab_gagal_ginjal_title) -> MenuInfo(
            title = context.getString(R.string.penyebab_gagal_ginjal_title),
            description = context.getString(R.string.penyebab_gagal_ginjal_description),
            bulletPoints = context.resources.getStringArray(R.array.penyebab_gagal_ginjal_bullet_points).toList(),
            imageResId = R.drawable.doctor_image3
        )
        context.getString(R.string.tanda_gejala_title) -> MenuInfo(
            title = context.getString(R.string.tanda_gejala_title),
            description = context.getString(R.string.tanda_gejala_description),
            bulletPoints = context.resources.getStringArray(R.array.tanda_gejala_bullet_points).toList(),
            imageResId = R.drawable.medical_result
        )
        context.getString(R.string.pemeriksaan_diagnosa_title) -> MenuInfo(
            title = context.getString(R.string.pemeriksaan_diagnosa_title),
            description = context.getString(R.string.pemeriksaan_diagnosa_description),
            bulletPoints = context.resources.getStringArray(R.array.pemeriksaan_diagnosa_bullet_points).toList(),
            imageResId = R.drawable.diagnosis
        )
        context.getString(R.string.pencegahan_title) -> MenuInfo(
            title = context.getString(R.string.pencegahan_title),
            description = context.getString(R.string.pencegahan_description),
            bulletPoints = context.resources.getStringArray(R.array.pencegahan_bullet_points).toList(),
            imageResId = R.drawable.health_solutions
        )
        context.getString(R.string.pengobatan_title) -> MenuInfo(
            title = context.getString(R.string.pengobatan_title),
            description = context.getString(R.string.pengobatan_description),
            bulletPoints = context.resources.getStringArray(R.array.pengobatan_bullet_points).toList(),
            imageResId = R.drawable.medical
        )
        context.getString(R.string.kondisi_lanjut_title) -> MenuInfo(
            title = context.getString(R.string.kondisi_lanjut_title),
            description = context.getString(R.string.kondisi_lanjut_description),
            bulletPoints = context.resources.getStringArray(R.array.kondisi_lanjut_bullet_points).toList(),
            imageResId = R.drawable.old_woman
        )
        else -> MenuInfo(
            title = context.getString(R.string.informasi_tidak_tersedia),
            description = context.getString(R.string.informasi_tidak_tersedia),
            imageResId = R.drawable.doctor_image
        )
    }
}