package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class NutritionThresholdResponse(

	@field:SerializedName("protein")
	val protein: Float? = null,

	@field:SerializedName("calories")
	val calories: Float? = null,

	@field:SerializedName("natrium")
	val sodium: Float? = null,

	@field:SerializedName("kalium")
	val potassium: Float? = null,

	@field:SerializedName("fluids")
	val fluids: Float? = null
)
