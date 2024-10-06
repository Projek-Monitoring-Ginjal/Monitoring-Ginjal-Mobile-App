package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ListMenuInfoGinjal
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.Route

enum class InformationMenuItem(
    val iconResId: Int,
    val titleTextResId: Int,
    val route: Route
) {
    KidneyFailure(
        iconResId = R.drawable.ic_kidney,
        titleTextResId = R.string.gagal_ginjal,
        route = ListMenuInfoGinjal(routeType = "Ginjal") // utk sementara
    ),
    Hemodialysis(
        iconResId = R.drawable.dialisis,
        titleTextResId = R.string.hemodialisa,
        route = ListMenuInfoGinjal(routeType = "Hemodialisa"), // utk sementara
    ),
    Diet(
        iconResId = R.drawable.ic_diet,
        titleTextResId = R.string.diet_atau_pengaturan_makan,
        route = ListMenuInfoGinjal(routeType = "Diet"), // utk sementara
    ),
    FluidNeeds(
        iconResId = R.drawable.ic_droplet,
        titleTextResId = R.string.kebutuhan_cairan,
        route = ListMenuInfoGinjal(routeType = "Cairan"), // utk sementara
    ),
    Nursing(
        iconResId = R.drawable.ic_nursing,
        titleTextResId = R.string.perawatan_akses_vaskuler_hemodialisa,
        route = ListMenuInfoGinjal(routeType = "Perawatan"), // utk sementara
    ),
}

