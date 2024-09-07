package com.neotelemetrixgdscunand.monitoringginjalapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.MonitoringGinjalAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMandeh(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme

    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.mandeh),
                contentDescription = "Logo Mandeh",
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 16.dp)
            )
        },
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colors.background
        ),
        modifier = modifier
    )
}


@Preview
@Composable
private fun TopBarViewPreview() {
    MonitoringGinjalAppTheme(darkTheme = true) {
        TopBarMandeh()
    }
}
