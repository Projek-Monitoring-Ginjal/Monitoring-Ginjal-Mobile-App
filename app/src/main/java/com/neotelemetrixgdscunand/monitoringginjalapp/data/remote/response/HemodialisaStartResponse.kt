package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class HemodialisaStartResponse(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("protein")
	val protein: Float? = null,

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("body_weight")
	val bodyWeight: Float? = null,

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
)
