package com.neotelemetrixgdscunand.monitoringginjalapp.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.component.MultiColorText
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Grey40
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.Yellow20
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.theme.karlaFamily

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var isSpinnerExpanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight(0.2f)
        ){
            MultiColorText(
                textWithColors = arrayOf(
                    Pair(
                        "MAN",
                        Yellow20
                    ),
                    Pair(
                        "DEH",
                        Green20
                    )
                )
            )
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            HeadingText(
                text = stringResource(R.string.masukkan_nama_dan_kata_sandi),
                color = Color.Black
            )



            LoginOutlinedTextField(
                labelText = stringResource(R.string.nama),
                value = name,
                onValueChange = { name = it },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null
                    )
                },
            )


            LoginOutlinedTextField(
                labelText = stringResource(R.string.kata_sandi),
                value = password,
                onValueChange = { password = it },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_key),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = null
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            LoginSpinner(
                onItemSelected = {
                    language = it
                    isSpinnerExpanded = false
                },
                onDismissRequest = {
                    isSpinnerExpanded = false
                },
                isExpanded = isSpinnerExpanded,
                onClick = {
                    isSpinnerExpanded = !isSpinnerExpanded
                },
                labelText = stringResource(id = R.string.pilih_bahasa),
                selectedText = language,
                options = listOf(
                    "Fajar",
                    "Alif"
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_language),
                        contentDescription = null
                    )
                } ,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = null
                    )
                }
            )
        }
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                keyboardController?.hide()
                focusManager.clearFocus(true)
                onLoginClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Green20
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            HeadingText(
                text = stringResource(R.string.masuk),
                color = Color.White,
                fontFamily = karlaFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.1.sp
            )
        }
    }


}

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

/*@Composable
fun LoginSpinner(
    modifier: Modifier = Modifier,
    isExpanded:Boolean = false,
    onDismissRequest:()->Unit = {},
    onItemSelected:()->Unit = {},
    options:List<String>
) {
    Row(
        modifier = modifier
            .padding(top = 12.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_language),
            tint = Grey20,
            contentDescription = null
        )
        HeadingText(
            text = stringResource(id = R.string.pilih_bahasa),
            color = Grey20
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_arrow_down),
            tint = Grey20,
            contentDescription = null,
        )
        DropdownMenu(modifier = modifier, expanded = isExpanded, onDismissRequest = onDismissRequest) {
            options.forEach {
                DropdownMenuItem(
                    onClick = onItemSelected,
                    text = { Text(text = it, color = Color.Black) }
                )
            }
        }
    }

}*/

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
/*@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginSpinnerPreview() {
    MonitoringGinjalAppTheme {
        var isExpanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            LoginSpinner(
                isExpanded = isExpanded,
                onItemSelected = {
                    isExpanded = false
                },
                onDismissRequest = {
                    isExpanded = false
                },
                options = listOf("Fajar", "Alif")
            )
        }

    }
    
}*/

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MonitoringGinjalAppTheme {
        Surface {
            LoginScreen()
        }
    }
}