package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodContentItemResponse(

    @field:SerializedName("id")
    val foodId:Int? = null,

    @field:SerializedName("sodium")
    val sodium: Float? = null,

    @field:SerializedName("potassium")
    val potassium: Float? = null,

    @field:SerializedName("satuan")
    val satuan: String? = null,

    @field:SerializedName("protein")
    val protein: Float? = null,

    @field:SerializedName("water")
    val water: Float? = null,

    @field:SerializedName("indonesia")
    val name: String? = null,

    @field:SerializedName("energy")
    val energy: Float? = null
)
