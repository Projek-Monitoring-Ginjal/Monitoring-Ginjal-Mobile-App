package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.screen


import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.viewmodel.LoginViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
) {

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isSpinnerExpanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val localeLanguageOptions = LoginUtil.getLanguageLocaleOptions(LocalContext.current)

    LaunchedEffect(key1 = viewModel.isSignedIn) {
        if (viewModel.isSignedIn == true) {
            onLoginClick()
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.ShowToast -> Toast.makeText(
                    context,
                    it.message.getValue(context),
                    Toast.LENGTH_SHORT
                ).show()

                else -> {}
            }
        }
    }


    if (viewModel.isSignedIn == false) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.2f)
            ) {
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
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = null
                        )
                    },
                )


                LoginOutlinedTextField(
                    labelText = stringResource(R.string.kata_sandi),
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
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
                            if (isPasswordVisible)
                                painterResource(id = R.drawable.ic_eye)
                            else painterResource(
                                id = R.drawable.ic_eye_hide
                            ),
                            contentDescription = null
                        )
                    },
                    visualTransformation = if (isPasswordVisible)
                        VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                LoginSpinner(
                    onItemSelected = {
                        viewModel.language = it
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
                    selectedText = viewModel.language,
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
                    },
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
                    viewModel.login(
                        localeLanguageOptions[viewModel.language] ?: "en"
                    )
                },
                backgroundColor = Green20,
                shape = RoundedCornerShape(16.dp),
                text = stringResource(R.string.masuk),
                textColor = Color.White,
                fontFamily = karlaFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.1.sp
            )

            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Green20
                )
            }
        }

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