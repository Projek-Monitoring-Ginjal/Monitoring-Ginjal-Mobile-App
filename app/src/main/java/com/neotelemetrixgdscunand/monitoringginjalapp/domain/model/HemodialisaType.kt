package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

enum class HemodialisaType(
    val value:Int,
    val daysCount:Int
){
    HEMODIALISA_1(1, 3),
    HEMODIALISA_2(2, 4);

    companion object{
        fun getHemodialisaType(value:Int):HemodialisaType{
            return when (value) {
                1 -> HEMODIALISA_1
                2 -> HEMODIALISA_2
                else -> throw Exception("Value is not valid..")
            }
        }
    }
}