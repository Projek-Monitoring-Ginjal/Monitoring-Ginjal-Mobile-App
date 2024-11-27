package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetHemodialisaResultResponse(
    @field:SerializedName("hemodialisa_type")
    val hemodialisaType:Int,

    @field:SerializedName("nutritionDay1")
    val nutritionThresholdDay1:NutritionThresholdResponse?,
    @field:SerializedName("nutritionDay2")
    val nutritionThresholdDay2:NutritionThresholdResponse?,
    @field:SerializedName("nutritionDay3")
    val nutritionThresholdDay3:NutritionThresholdResponse?,
    @field:SerializedName("nutritionDay4")
    val nutritionThresholdDay4:NutritionThresholdResponse?,

    @field:SerializedName("countNutritionDay1")
    val countNutritionDay1:NutritionThresholdResponse?,
    @field:SerializedName("countNutritionDay2")
    val countNutritionDay2:NutritionThresholdResponse?,
    @field:SerializedName("countNutritionDay3")
    val countNutritionDay3:NutritionThresholdResponse?,
    @field:SerializedName("countNutritionDay4")
    val countNutritionDay4:NutritionThresholdResponse?,
)