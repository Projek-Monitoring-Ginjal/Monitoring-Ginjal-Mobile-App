package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.BodyWeightInput
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route

data class MainMenuItem(
    val id:Int,
    val iconResId:Int,
    val titleTextResId:Int,
    val route:Route
)

fun getMainMenuItems():List<MainMenuItem>{
    return listOf(
        MainMenuItem(
            id = 1,
            iconResId = R.drawable.ic_kidney,
            titleTextResId = R.string.gagal_ginjal,
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 2,
            iconResId = R.drawable.ic_infusion,
            titleTextResId = R.string.hemodialisa,
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 3,
            iconResId = R.drawable.ic_diet,
            titleTextResId = R.string.diet_atau_pengaturan_makan,
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 4,
            iconResId = R.drawable.ic_droplet,
            titleTextResId = R.string.kebutuhan_cairan,
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 5,
            iconResId = R.drawable.ic_nursing,
            titleTextResId = R.string.perawatan_akses_vaskuler_hemodialisa,
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 6,
            iconResId = R.drawable.ic_eats,
            titleTextResId = R.string.pengaturan_makan_dan_minum_saya_hari_ini,
            BodyWeightInput,
        )
    )
}