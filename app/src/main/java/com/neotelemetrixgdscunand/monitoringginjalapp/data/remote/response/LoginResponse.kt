package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
)
