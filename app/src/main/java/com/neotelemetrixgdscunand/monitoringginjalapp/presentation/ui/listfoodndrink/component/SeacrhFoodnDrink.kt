package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Dummy
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Neutral05
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.PurpleGrey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    foodItems: List<FoodItem>,
    onAddClick: (FoodItem) -> Unit,
    isListVisible: Boolean = false,
    searchFoodItems:(String) -> Unit = { },
    setListVisibility: (Boolean) -> Unit = {}
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val containerColor = colorResource(R.color.lightGrey)
    //var isListVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        HeadingText(
            text = stringResource(R.string.cari_makanan_dan_minuman_saya),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color =  PurpleGrey40
        )

        Spacer(modifier =  Modifier.height(8.dp))

        TextField(
            singleLine = true,
            value = searchText,
            onValueChange = { newValue ->
                if(searchText.text != "" && newValue.text == ""){
                    setListVisibility(false)
                }else{
                    setListVisibility(true)
                    searchFoodItems(newValue.text)
                }
                searchText = newValue
            },
            placeholder = {
                Text(text = "Cari", color = Neutral05, fontSize = 16.sp)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Neutral05)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(16.dp))
                .border(1.dp, Green20,shape = RoundedCornerShape(16.dp))
                .clickable {
                    setListVisibility(true)
                },
            shape = RoundedCornerShape(16.dp),
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

        if(isListVisible){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                items(foodItems) { foodItem ->
                    FoodItemRow(foodItem, onAddClick = {
                        onAddClick(it)
                        searchText = TextFieldValue("")
                        setListVisibility(false)
                        //isListVisible = false
                    })
                }
            }
        }
    }
}





@Composable
fun FoodItemRow(food: FoodItem, onAddClick: (FoodItem) -> Unit) {
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
                Text(text = food.name, fontSize = 18.sp, color = Color.Black)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    val nutritionEssential = food.nutritionEssential

                    NutrientInfo(
                        text = stringResource(id = R.string.kalori),
                        value = nutritionEssential.calorie.amount.toString()
                    )
                    NutrientInfo(
                        text = stringResource(id = R.string.cairan),
                        value = nutritionEssential.fluid.amount.toString()
                    )
                    NutrientInfo(
                        text = stringResource(id = R.string.protein),
                        value = nutritionEssential.protein.amount.toString()
                    )
                    NutrientInfo(
                        text = stringResource(id = R.string.natrium),
                        value = nutritionEssential.sodium.amount.toString()
                    )
                    NutrientInfo(
                        text = stringResource(id = R.string.kalium),
                        value = nutritionEssential.calorie.amount.toString()
                    )
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

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    val foodItems = Dummy.getFoodItems()

    SearchBar(
        foodItems = foodItems,
        onAddClick = { /* Handle Add Click */ },
        isListVisible = true,
        setListVisibility = {}
    )
}
