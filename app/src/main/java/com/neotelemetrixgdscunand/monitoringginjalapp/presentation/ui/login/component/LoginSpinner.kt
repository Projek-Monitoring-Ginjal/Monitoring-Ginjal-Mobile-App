package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSpinner(
    modifier: Modifier = Modifier,
    selectedText:String = "",
    labelText:String = "",
    isExpanded:Boolean = false,
    onClick:(Boolean)->Unit = {},
    onItemSelected: (String) -> Unit = { },
    options:List<String> = listOf(),
    onDismissRequest:()->Unit = {},
    marginStart: Dp = 16.dp,
    marginEnd: Dp = 16.dp,
    marginTop: Dp = 12.dp,
    marginBottom: Dp = 0.dp,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {}
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(marginStart, marginTop, marginEnd, marginBottom)
    ) {
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            singleLine = true,
            value = selectedText, onValueChange = {  },
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
                cursorColor = Color.White,
                errorCursorColor = Color.White,
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
            /*interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect{
                        if(it is PressInteraction.Release){
                            onClick()
                        }
                    }
                }
            }*/
        )

        ExposedDropdownMenu(
            modifier = Modifier.background(Grey40),
            expanded = isExpanded,
            onDismissRequest = onDismissRequest) {

            options.forEach {
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(it)
                    },
                    text = {
                        HeadingText(
                            text = it,
                            color = Color.Black
                        )
                    }
                )
            }
        }
    }

}