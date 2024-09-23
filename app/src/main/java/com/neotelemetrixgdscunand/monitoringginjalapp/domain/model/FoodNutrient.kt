package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import com.fajar.githubuserappdicoding.core.domain.common.StaticString
import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.R

sealed class FoodNutrient(val name:StringRes, val unit:StringRes, val amount:Float){

    class Calorie(
        amount:Float = 0f
    ):FoodNutrient(
        StaticString(R.string.kalori),
        StaticString(R.string.kkal),
        amount
    )
    class Fluid(amount:Float = 0f):FoodNutrient(StaticString(R.string.cairan), StaticString(R.string.ml), amount)
    class Protein(amount:Float = 0f):FoodNutrient(StaticString(R.string.protein), StaticString(R.string.mg), amount)
    class Natrium(amount:Float = 0f):FoodNutrient(StaticString(R.string.natrium), StaticString(R.string.mg), amount)
    class Kalium(amount:Float = 0f):FoodNutrient(StaticString(R.string.kalium), StaticString(R.string.mg), amount)
}