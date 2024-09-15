package com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.neotelemetrixgdscunand.monitoringginjalapp.R

data class FoodItemData(
    val foodName: String,
    val calories: String,
    val volume: String,
    val protein: String,
    val sodium: String,
    val potassium: String
)

@Composable
fun FoodItem(
    food: FoodItemData,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.lightGrey)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = food.foodName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.kalori),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.kkal, food.calories),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    Text(
                        text = stringResource(R.string.cairan),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.ml, food.volume),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    Text(
                        text = stringResource(R.string.protein),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.gr, food.protein),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    Text(
                        text = stringResource(R.string.natrium),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.mg, food.sodium),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    Text(
                        text = stringResource(R.string.kalium),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.mg, food.potassium),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.align(Alignment.Bottom)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = "Delete",
                        tint = colorResource(R.color.green),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FoodList(foodItems: List<FoodItemData>, onDeleteClick: (FoodItemData) -> Unit) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        foodItems.forEach { food ->
            FoodItem(
                food = food,
                onDeleteClick = { onDeleteClick(food) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodList() {
    val foodItems = listOf(
        FoodItemData("Nasi Goreng", "625", "200", "8.8", "22.5", "107"),
        FoodItemData("Udang Segar", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Telur Ceplok", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Udang Segar", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Telur Ceplok", "625", "50", "8.8", "22.5", "107")
    )

    FoodList(
        foodItems = foodItems,
        onDeleteClick = {  }
    )
}
