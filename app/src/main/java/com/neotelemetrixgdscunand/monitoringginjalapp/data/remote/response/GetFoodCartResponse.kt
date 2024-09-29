package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetFoodCartResponse(

	@field:SerializedName("list_makanan")
	val foodCart: List<FoodItemCartResponse>? = null,

	@field:SerializedName("nutrition_treshold")
	val nutritionTreshold: NutritionThresholdResponse? = null
)

