package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class InputUrineResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("calories")
	val calories: Float? = null,

	@field:SerializedName("natrium")
	val sodium: Float? = null,

	@field:SerializedName("kalium")
	val potassium: Float? = null,

	@field:SerializedName("fluids")
	val fluids: Float? = null,

	@field:SerializedName("protein")
	val protein: Float? = null,


)
