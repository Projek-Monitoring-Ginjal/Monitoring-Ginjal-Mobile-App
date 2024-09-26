package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.core.Fit
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme

@Composable
fun ComposableRiveAnimationView(
    modifier: Modifier = Modifier,
    @RawRes animation: Int,
    stateMachineName: String? = null,
    alignment: app.rive.runtime.kotlin.core.Alignment = app.rive.runtime.kotlin.core.Alignment.CENTER,
    fit: Fit = Fit.CONTAIN,
    onInit: (RiveAnimationView) -> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RiveAnimationView(context).also {
                it.setRiveResource(
                    resId = animation,
                    stateMachineName = stateMachineName,
                    alignment = alignment,
                    fit = fit,

                    )
            }
        },
        update = { view -> onInit(view) }
    )
}

@Preview
@Composable
private fun ComposableRiveAnimationViewPreview() {
    MonitoringGinjalAppTheme {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.Blue)
        ) {
            ComposableRiveAnimationView(animation = R.raw.animasi_berpikir)
        }

    }
}