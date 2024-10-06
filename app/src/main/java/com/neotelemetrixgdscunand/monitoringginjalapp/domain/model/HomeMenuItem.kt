package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.DailyNutrientCalc
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.InformationMenu
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route

enum class HomeMenuItem(
    val iconResId: Int,
    val titleTextResId: Int,
    val route:Route
) {

    FoodArrangment(
        iconResId = R.drawable.ic_eats,
        titleTextResId = R.string.pengaturan_makan_dan_minum_saya_hari_ini,
        DailyNutrientCalc,
    ),

    InformationEducation(
        iconResId = R.drawable.ic_information_education,
        titleTextResId = R.string.informasi_dan_edukasi,
        route = InformationMenu,
    )

}