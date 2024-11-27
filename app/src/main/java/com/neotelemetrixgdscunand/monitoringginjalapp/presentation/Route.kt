package com.neotelemetrixgdscunand.monitoringginjalapp.presentation

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import kotlinx.serialization.Serializable


@Serializable
sealed class Route
@Serializable
data object Login: Route()
@Serializable
data object HomeMenu: Route()
@Serializable
data object InformationMenu:Route()
@Serializable
data class DailyNutrientCalc(val hemodialisaType:HemodialisaType) : Route()
@Serializable
data class ListFoodnDrink(val dayOptions: DayOptions, val hemodialisaType: HemodialisaType) : Route()
@Serializable
data class MealResult(val dayOptions: DayOptions, val hemodialisaType: HemodialisaType):Route()
@Serializable
data class ListMenuInfoGinjal(val routeType: String) : Route()


