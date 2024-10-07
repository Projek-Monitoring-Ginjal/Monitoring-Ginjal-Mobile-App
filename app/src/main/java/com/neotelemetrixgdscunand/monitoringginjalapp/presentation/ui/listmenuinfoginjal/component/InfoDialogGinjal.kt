package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listmenuinfoginjal.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.karlaFamily

@Composable
fun InfoDialogGinjal(
    title: String,
    description: String,
    bulletPoints: List<String>,
    imageResId: Int,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    color = Color.Black,
                    text = title,
                    fontFamily = karlaFamily,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .shadow(elevation = 1.dp, spotColor = Color.DarkGray))
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .heightIn(this@BoxWithConstraints.maxHeight/5f, this@BoxWithConstraints.maxHeight / 2f)
                        .padding(8.dp),
                ) {

                    item {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = "Illustration",
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = description,
                            fontFamily = karlaFamily,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    items(bulletPoints.size) { index ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFFFFF9C4), RoundedCornerShape(4.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "${index + 1}. ${bulletPoints[index]}",
                                fontFamily = karlaFamily,
                                textAlign = TextAlign.Justify,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                Button(
                    onClick = { onDismiss() },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.green)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.kembali),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoDialog() {
    InfoDialogGinjal(
        title = "Tanda dan gejala",
        description = "Gagal ginjal kronis adalah menurunnya fungsi ginjal yang terjadi secara tiba-tiba, umumnya terjadi selama beberapa jam sampai beberapa hari dan mengakibatkan terjadinya gangguan pada cairan, elektrolit dan asam basa pada tubuh.",
        bulletPoints = listOf(
            "Obat-obatan untuk mengurangi gejala seperti obat hipertensi, anemia, suplemen kalsium dan vitamin D.",
            "Perubahan pola makan dan asupan cairan untuk mengurangi beban kerja ginjal.",
            "Dialisis atau cuci darah untuk membantu proses penyaringan darah."
        ),
        imageResId = R.drawable.doctor_image,
        onDismiss = {}
    )
}
