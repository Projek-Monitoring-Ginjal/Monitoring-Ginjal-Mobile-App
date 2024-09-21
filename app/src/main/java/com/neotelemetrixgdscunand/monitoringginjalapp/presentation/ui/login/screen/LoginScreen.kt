package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.karlaFamily
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.LoginOutlinedTextField
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.LoginSpinner
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.MultiColorText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.util.LoginUtil

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var language by rememberSaveable { mutableStateOf("") }
    var isSpinnerExpanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val localeLanguageOptions = LoginUtil.getLanguageLocaleOptions(LocalContext.current)

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
                        modifier = Modifier
                            .clickable {
                                 isPasswordVisible = !isPasswordVisible
                            },
                        painter =
                        if(isPasswordVisible)
                            painterResource(id = R.drawable.ic_eye)
                        else painterResource(
                            id = R.drawable.ic_eye_hide),
                        contentDescription = null
                    )
                },
                visualTransformation = if (isPasswordVisible)
                    VisualTransformation.None
                else PasswordVisualTransformation()
            )

            LoginSpinner(
                onItemSelected = {
                    language = it
                    isSpinnerExpanded = false
                    val newLanguageCode = localeLanguageOptions[it] ?: "in"
                    LoginUtil.setNewLanguageLocale(newLanguageCode)
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
                    stringResource(R.string.indonesia),
                    stringResource(R.string.english),
                    stringResource(R.string.minang)
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
        StyledButton(
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
            backgroundColor = Green20,
            shape = RoundedCornerShape(16.dp),
            text = stringResource(R.string.masuk),
            textColor = Color.White,
            fontFamily = karlaFamily,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.1.sp
        )
    }
}








@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MonitoringGinjalAppTheme {
        Surface {
            LoginScreen()
        }
    }
}