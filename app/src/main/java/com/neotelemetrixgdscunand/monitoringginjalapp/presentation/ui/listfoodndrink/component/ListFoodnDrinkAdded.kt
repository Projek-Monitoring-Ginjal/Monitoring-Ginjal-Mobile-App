package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Dummy
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemCart
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText


@Composable
fun FoodItem(
    food: FoodItemCart,
    onDeleteClick: () -> Unit
) {
    val nutritionEssential = food.foodItem.nutritionEssential
    val context = LocalContext.current

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
                text = food.foodItem.name,
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
                    HeadingText(
                        text = stringResource(R.string.kalori),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    HeadingText(
                        text = "${nutritionEssential.calorie.amount} ${nutritionEssential.calorie.unit.getValue(context)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    HeadingText(
                        text = stringResource(R.string.cairan),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    HeadingText(
                        text = "${nutritionEssential.fluid.amount} ${nutritionEssential.fluid.unit.getValue(context)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    HeadingText(
                        text = stringResource(R.string.protein),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    HeadingText(
                        text = "${nutritionEssential.protein.amount} ${nutritionEssential.protein.unit.getValue(context)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    HeadingText(
                        text = stringResource(R.string.natrium),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    HeadingText(
                        text = "${nutritionEssential.sodium.amount} ${nutritionEssential.sodium.unit.getValue(context)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column {
                    HeadingText(
                        text = stringResource(R.string.kalium),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    HeadingText(
                        text = "${nutritionEssential.potassium.amount} ${nutritionEssential.potassium.unit.getValue(context)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                }
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.align(Alignment.Top)
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
fun FoodList(foodItems: List<FoodItemCart>, onDeleteClick: (FoodItemCart) -> Unit) {

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
            Text(text = "sdfsdf")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodList() {

    val foodItemsCart = remember {
        Dummy.getFoodItemsCart()
    }

    FoodList(
        foodItems = foodItemsCart,
        onDeleteClick = {  }
    )
}
