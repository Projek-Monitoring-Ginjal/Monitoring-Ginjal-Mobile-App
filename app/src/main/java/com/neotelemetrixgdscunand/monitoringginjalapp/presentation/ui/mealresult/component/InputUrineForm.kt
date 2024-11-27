package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.component.FormField
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton

@Composable
fun InputUrineForm(
    modifier: Modifier = Modifier,
    textState:String = "",
    onTextChange:(String) -> Unit = {},
    onSaveClicked:() -> Unit = {}
) {
    Column(modifier = modifier){
        FormField(
            value = textState,
            onValueChange = { onTextChange(it) },
            placeholder = stringResource(R.string.urine_ml),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 50.dp),
            padding = PaddingValues(0.dp)
        )

        Spacer(Modifier.height(16.dp))

        StyledButton(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 50.dp)
                .padding(horizontal = 0.dp),
            text = stringResource(R.string.simpan),
            onClick = onSaveClicked
        )

        Spacer(Modifier.height(16.dp))
    }

}

@Preview(showBackground = true)
@Composable
private fun InputUrineFormPreview() {
    MonitoringGinjalAppTheme {
        InputUrineForm()
    }

}