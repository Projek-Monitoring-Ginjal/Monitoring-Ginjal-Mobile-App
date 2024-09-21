package com.neotelemetrixgdscunand.monitoringginjalapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.neotelemetrixgdscunand.monitoringginjalapp.App
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// AppCompatActivity, for language locale changes
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonitoringGinjalAppTheme(dynamicColor = false){
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }

            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonitoringGinjalAppTheme {
        App()
    }
}