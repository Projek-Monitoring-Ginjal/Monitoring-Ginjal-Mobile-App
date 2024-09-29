package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.fajar.githubuserappdicoding.core.domain.common.DynamicString
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.UserPreference
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemCart
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodPortionOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


val Context.dataStore by preferencesDataStore(name = UserPreference.NAME)


fun <T> LifecycleOwner.collectChannelFlowOnLifecycleStarted(
    flow: Flow<T>,
    onCollect: suspend (T) -> Unit
) {
    this.lifecycleScope.launch {
        this@collectChannelFlowOnLifecycleStarted.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onCollect)
            }
        }
    }
}

fun <T:Any> NavHostController.navigateWithCheck(route : T){
    if(currentBackStackEntry?.lifecycle?.currentState  == Lifecycle.State.RESUMED){
        navigate(route)
    }
}

fun <T:Any> NavHostController.navigateWithCheck(route : T, builder : NavOptionsBuilder.() -> Unit){
    if(currentBackStackEntry?.lifecycle?.currentState  == Lifecycle.State.RESUMED){
        navigate(
            route = route,
            builder = builder
        )
    }
}


suspend fun <T> Resource<T>.handleAsyncDefaultWithUIEvent(
    uiEventChannel: Channel<UIEvent>,
    onSuccess: suspend (T) -> Unit = { }
) {
    when (this) {
        is Resource.Success -> onSuccess(data)
        is Resource.Failure -> {
            uiEventChannel.send(
                UIEvent.ShowToast(this.message)
            )
        }
        is Resource.Error -> {
            uiEventChannel.send(
                UIEvent.ShowToast(
                    DynamicString(this.e.message.toString())
                )
            )
        }
    }
}

fun FoodItem.changePortion(portion: FoodPortionOptions):FoodItemCart {
    val foodItemNutrition = this.nutritionEssential
    val multiplier = portion.multiplier

    val adjustedFoodItem = foodItemNutrition.let {
        this.copy(
            nutritionEssential = NutritionEssential(
                calorie = FoodNutrient.Calorie(
                    amount = it.calorie.amount * multiplier
                ),
                fluid = FoodNutrient.Fluid(
                    amount = it.fluid.amount * multiplier
                ),
                protein = FoodNutrient.Protein(
                    amount = it.protein.amount * multiplier
                ),
                sodium = FoodNutrient.Sodium(
                    amount = it.sodium.amount * multiplier
                ),
                potassium = FoodNutrient.Potassium(
                    amount = it.potassium.amount * multiplier
                ),
            )
        )
    }
    return FoodItemCart(
        foodItem = adjustedFoodItem,
        portionOptions = portion
    )
}
