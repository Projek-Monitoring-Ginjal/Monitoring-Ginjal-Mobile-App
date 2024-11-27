package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

enum class HemodialisaType(
    val value:Int,
){
    HEMODIALISA_1(1),
    HEMODIALISA_2(2);

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