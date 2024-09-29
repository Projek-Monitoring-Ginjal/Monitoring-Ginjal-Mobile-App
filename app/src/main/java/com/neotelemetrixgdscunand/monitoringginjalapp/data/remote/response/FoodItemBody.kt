package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response

import com.google.gson.annotations.SerializedName


data class FoodItemRequestBody(
    @field:SerializedName("listMakan")
    val listMakanan:List<FoodItemBody>
)
data class FoodItemBody(
    @field:SerializedName("makanan_id")
    val makananId:Int,
    @field:SerializedName("portion")
    val portion:Float
)