package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckHemodialisaResponse(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("protein")
	val protein: Any? = null,

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("body_weight")
	val bodyWeight: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("calories")
	val calories: Any? = null,

	@field:SerializedName("natrium")
	val natrium: Int? = null,

	@field:SerializedName("kalium")
	val kalium: Int? = null,

	@field:SerializedName("fluids")
	val fluids: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
