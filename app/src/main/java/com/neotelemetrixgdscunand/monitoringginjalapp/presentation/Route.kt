package com.neotelemetrixgdscunand.monitoringginjalapp.presentation

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import kotlinx.serialization.Serializable


@Serializable
sealed class Route
@Serializable
data object Login: Route()
@Serializable
data object HomeMenu: Route()
@Serializable
data object DailyNutrientCalc : Route()
@Serializable
data class ListFoodnDrink(val dayOptions: DayOptions) : Route()
@Serializable
data class MealResult(val dayOptions: DayOptions):Route()

