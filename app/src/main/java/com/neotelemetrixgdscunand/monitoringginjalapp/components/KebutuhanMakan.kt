package com.neotelemetrixgdscunand.monitoringginjalapp.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Typography


@Composable
fun NutrientCard(
    calories: Int = 2100,
    protein: Int = 45,
    fat: Int = 67,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6E9B2)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kebutuhan makan saya sehari",
                style = Typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.woman_eating_healthy_food_img), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Crop
            )

            // Nutrient Information
            NutrientInfoRow("Kalori (kalori)", calories.toString())
            NutrientInfoRow("Protein (gram)", protein.toString())
            NutrientInfoRow("Lemak (gram)", fat.toString())
        }
    }
}

@Composable
fun NutrientInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF3CA52)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = Typography.bodyLarge,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 16.sp
        )
        Text(
            text = value,
            modifier = Modifier
                .padding(8.dp),
            style = Typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}


@Preview
@Composable
fun NutrientCardPreview() {
    NutrientCard()
}
