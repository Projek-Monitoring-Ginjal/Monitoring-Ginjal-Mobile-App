package com.neotelemetrixgdscunand.monitoringginjalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.App
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.MonitoringGinjalAppTheme

class MainActivity : ComponentActivity() {
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