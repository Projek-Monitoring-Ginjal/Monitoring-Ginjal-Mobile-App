package com.neotelemetrixgdscunand.monitoringginjalapp.ui.login.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Yellow20

@Composable
fun LoginOutlinedTextField(
    modifier: Modifier = Modifier,
    value:String = "",
    labelText:String = "",
    onValueChange: (String) -> Unit = { },
    marginStart: Dp = 16.dp,
    marginEnd: Dp = 16.dp,
    marginTop: Dp = 12.dp,
    marginBottom: Dp = 0.dp,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    OutlinedTextField(
        modifier = modifier
            .padding(marginStart, marginTop, marginEnd, marginBottom)
            .fillMaxWidth(),
        singleLine = true,
        value = value, onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            unfocusedLeadingIconColor = Grey20,
            unfocusedContainerColor = Grey40,
            unfocusedLabelColor = Grey20,
            unfocusedTrailingIconColor = Grey20,
            focusedLeadingIconColor = Yellow20,
            focusedBorderColor = Green20,
            focusedLabelColor = Yellow20,
            focusedTrailingIconColor = Yellow20,
        ),
        label = {
            HeadingText(
                text = labelText,
                color = Color.Unspecified
            )
        },
        shape = RoundedCornerShape(14.dp),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource
    )
}