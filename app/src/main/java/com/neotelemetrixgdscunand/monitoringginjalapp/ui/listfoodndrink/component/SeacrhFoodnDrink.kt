package com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.model.FoodItemData

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchFoodItems: List<FoodItemData>,
    onAddClick: (FoodItemData) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var isListVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(8.dp)) {
        val containerColor = colorResource(R.color.lightGrey)
        TextField(
            value = searchText,
            onValueChange = { newValue ->
                searchText = newValue
                isListVisible = newValue.text.isNotEmpty() || newValue.text.isEmpty()
            },
            placeholder = {
                Text(text = "Cari", color = Color.LightGray, fontSize = 16.sp)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.LightGray)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(25.dp))
                .clickable {
                    isListVisible = true
                },
            shape = RoundedCornerShape(25.dp),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 16.sp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )

        if (isListVisible) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                items(searchFoodItems.filter { it.foodName.contains(searchText.text, ignoreCase = true) || searchText.text.isEmpty() }) { foodItem ->
                    FoodItemRow(foodItem, onAddClick = {
                        onAddClick(it)
                        searchText = TextFieldValue("")
                        isListVisible = false
                    })
                }
            }
        }
    }
}





@Composable
fun FoodItemRow(food: FoodItemData, onAddClick: (FoodItemData) -> Unit) {
    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.lightGrey))
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Handle row click */ }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = food.foodName, fontSize = 18.sp, color = Color.Black)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NutrientInfo(text = "Kalori", value = food.calories)
                    NutrientInfo(text = "Cairan", value = food.volume)
                    NutrientInfo(text = "Protein", value = food.protein)
                    NutrientInfo(text = "Natrium", value = food.sodium)
                    NutrientInfo(text = "Kalium", value = food.potassium)
                IconButton(onClick = { onAddClick(food) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to Cart",
                        tint = colorResource(R.color.green)
                    )
                }
                }

            }
        }
    }
}


@Composable
fun NutrientInfo(text: String, value: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = text, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontSize = 14.sp, color = Color.Black, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    val foodItems = listOf(
        FoodItemData("Ati ayam", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg"),
        FoodItemData("Bubur nasi", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg"),
        FoodItemData("Cumi-cumi", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg"),
        FoodItemData("Daging ayam", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg"),
        FoodItemData("Bubur nasi", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg"),
        FoodItemData("Cumi-cumi", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg"),
        FoodItemData("Daging ayam", "625 kkal", "50 ml", "8.8 mg", "22.5 mg", "107 mg")
    )

    SearchBar(
        searchFoodItems = foodItems,
        onAddClick = { /* Handle Add Click */ }
    )
}
