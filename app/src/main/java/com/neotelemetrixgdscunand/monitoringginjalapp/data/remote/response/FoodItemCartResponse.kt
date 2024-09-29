package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodItemCartResponse(

	@field:SerializedName("date")
	val day: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("makanan_id")
	val makananId: Int? = null,

	@field:SerializedName("Makanan")
	val makanan: FoodContentItemResponse? = null,

	@field:SerializedName("portion")
	val portion: Float? = null,

	@field:SerializedName("date_check")
	val dateCheck: String? = null
)

