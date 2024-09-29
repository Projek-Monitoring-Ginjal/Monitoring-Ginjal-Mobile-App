package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetHemodialisaResultResponse(
    @field:SerializedName("nutrition")
    val nutritionThreshold:NutritionThresholdResponse,
    @field:SerializedName("countNutritionDay1")
    val countNutritionDay1:NutritionThresholdResponse,
    @field:SerializedName("countNutritionDay2")
    val countNutritionDay2:NutritionThresholdResponse,
    @field:SerializedName("countNutritionDay3")
    val countNutritionDay3:NutritionThresholdResponse,
    @field:SerializedName("countNutritionDay4")
    val countNutritionDay4:NutritionThresholdResponse,
)