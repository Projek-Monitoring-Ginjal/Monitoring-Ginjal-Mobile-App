package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import android.content.Context
import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class HemodialisaMenuInfo(
    val title: String,
    val description: String,
    val bulletPoints: List<String> = emptyList(),
    val imageResId: Int
)

fun getHemodialisaMenuInfo(context: Context, title: String): HemodialisaMenuInfo {
    return when (title) {
        context.getString(R.string.pengertian_hemodialisa_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.pengertian_hemodialisa_title),
            description = context.getString(R.string.pengertian_hemodialisa),
            imageResId = R.drawable.pengertian_hd
        )
        context.getString(R.string.tujuan_hemodialisa_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.tujuan_hemodialisa_title),
            description = context.getString(R.string.tujuan_hemodialisa),
            imageResId = R.drawable.tujuan
        )
        context.getString(R.string.kapan_melakukan_hemodialisa_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.kapan_melakukan_hemodialisa_title),
            description = context.getString(R.string.kapan_melakukan_hemodialisa_title),
            bulletPoints = context.resources.getStringArray(R.array.kapan_melakukan_hemodialisa_bullet_points).toList(),
            imageResId = R.drawable.kapan_hd
        )
        context.getString(R.string.bagaimana_melakukan_hemodialisa_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.bagaimana_melakukan_hemodialisa_title),
            description = context.getString(R.string.bagaimana_melakukan_hemodialisa_title),
            bulletPoints = context.resources.getStringArray(R.array.bagaimana_melakukan_hemodialisa_bullet_points).toList(),
            imageResId = R.drawable.bagaimana_hd
        )
        context.getString(R.string.efek_samping_hemodialisa_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.efek_samping_hemodialisa_title),
            description = context.getString(R.string.efek_samping_hemodialisa_title),
            bulletPoints = context.resources.getStringArray(R.array.efek_samping_hemodialisa_bullet_points).toList(),
            imageResId = R.drawable.efek_samping
        )
        context.getString(R.string.dampak_telat_cuci_darah_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.dampak_telat_cuci_darah_title),
            description = context.getString(R.string.dampak_telat_cuci_darah_title),
            bulletPoints = context.resources.getStringArray(R.array.dampak_telat_cuci_darah_bullet_points).toList(),
            imageResId = R.drawable.dampak_telat
        )
        context.getString(R.string.di_mana_melakukan_hemodialisa_title) -> HemodialisaMenuInfo(
            title = context.getString(R.string.di_mana_melakukan_hemodialisa_title),
            description = context.getString(R.string.di_mana_melakukan_hemodialisa),
            imageResId = R.drawable.dimana_hd
        )
        else -> HemodialisaMenuInfo(
            title = context.getString(R.string.informasi_tidak_tersedia),
            description = context.getString(R.string.informasi_tidak_tersedia),
            imageResId = R.drawable.doctor_image
        )
    }
}
