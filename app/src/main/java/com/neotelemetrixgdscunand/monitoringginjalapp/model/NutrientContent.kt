package com.neotelemetrixgdscunand.monitoringginjalapp.model

data class NutrientContent(
    val name:String,
    val value:Int,
    val unit:String,
)

fun getNutrientContents():List<NutrientContent>{
    return listOf(
        NutrientContent(
            "Kalori",
            2400,
            "kal"
        ),
        NutrientContent(
            "Cairan",
            600,
                "ml"
        ),
        NutrientContent(
            "Protein",
            79200,
            "mg"
        ),
        NutrientContent(
            "Natrium",
            2600,
            "mg"
        ),
        NutrientContent(
            "Kalium",
            2100,
            "mg"
        ),

    )
}