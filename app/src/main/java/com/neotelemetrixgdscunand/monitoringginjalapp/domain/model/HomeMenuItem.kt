package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R

enum class HomeMenuItem(
    val iconResId: Int,
    val titleTextResId: Int
) {

    FoodArrangment(
        iconResId = R.drawable.ic_eats,
        titleTextResId = R.string.pengaturan_makan_dan_minum_saya_hari_ini
    ),

    InformationEducation(
        iconResId = R.drawable.ic_information_education,
        titleTextResId = R.string.informasi_dan_edukasi
    )

}