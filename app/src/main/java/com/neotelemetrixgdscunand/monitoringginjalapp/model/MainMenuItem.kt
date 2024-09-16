package com.neotelemetrixgdscunand.monitoringginjalapp.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.BodyWeightInput
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.Route

data class MainMenuItem(
    val id:Int,
    val iconResId:Int,
    val title:String,
    val route:Route
)

fun getMainMenuItems():List<MainMenuItem>{
    return listOf(
        MainMenuItem(
            id = 1,
            iconResId = R.drawable.ic_kidney,
            title = "Gagal Ginjal",
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 2,
            iconResId = R.drawable.ic_infusion,
            title = "Hemodialisa",
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 3,
            iconResId = R.drawable.ic_diet,
            title = "Diet atau pengaturan makan",
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 4,
            iconResId = R.drawable.ic_droplet,
            title = "Kebutuhan Cairan",
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 5,
            iconResId = R.drawable.ic_nursing,
            title = "Perawatan akses vaskuler hemodialisa",
            BodyWeightInput, // utk sementara
        ),
        MainMenuItem(
            id = 6,
            iconResId = R.drawable.ic_eats,
            title = "Pengaturan makan dan minum saya hari ini",
            BodyWeightInput,
        )
    )
}