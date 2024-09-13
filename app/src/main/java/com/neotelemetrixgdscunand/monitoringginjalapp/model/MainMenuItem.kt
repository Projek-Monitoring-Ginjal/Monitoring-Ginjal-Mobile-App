package com.neotelemetrixgdscunand.monitoringginjalapp.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class MainMenuItem(
    val id:Int,
    val iconResId:Int,
    val title:String
)

fun getMainMenuItems():List<MainMenuItem>{
    return listOf(
        MainMenuItem(
            id = 1,
            iconResId = R.drawable.ic_kidney,
            title = "Gagal Ginjal"
        ),
        MainMenuItem(
            id = 2,
            iconResId = R.drawable.ic_infusion,
            title = "Hemodialisa"
        ),
        MainMenuItem(
            id = 3,
            iconResId = R.drawable.ic_diet,
            title = "Diet atau pengaturan makan"
        ),
        MainMenuItem(
            id = 4,
            iconResId = R.drawable.ic_droplet,
            title = "Kebutuhan Cairan"
        ),
        MainMenuItem(
            id = 5,
            iconResId = R.drawable.ic_nursing,
            title = "Perawatan akses vaskuler hemodialisa"
        ),
        MainMenuItem(
            id = 6,
            iconResId = R.drawable.ic_food,
            title = "Pengaturan makan saya hari ini"
        ),
        MainMenuItem(
            id = 7,
            iconResId = R.drawable.ic_drink,
            title = "Pengaturan cairan dan minum saya hari ini"
        ),
    )
}