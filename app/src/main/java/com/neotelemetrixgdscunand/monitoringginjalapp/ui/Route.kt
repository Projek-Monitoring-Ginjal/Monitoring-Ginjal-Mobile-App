package com.neotelemetrixgdscunand.monitoringginjalapp.ui

import kotlinx.serialization.Serializable


@Serializable
sealed class Route
@Serializable
data object Login: Route()
@Serializable
data object HomeMenu: Route()
@Serializable
data object BodyWeightInput : Route()
@Serializable
data object ListFoodnDrink : Route()
@Serializable
data object MealResult:Route()

