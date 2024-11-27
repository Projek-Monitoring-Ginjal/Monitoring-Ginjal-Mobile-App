package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.HeadingText
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton

@Composable
fun ConfirmationDialog(
    text:String = "",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    isShown:Boolean = false
) {
    if(isShown){
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 48.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column{
                    HeadingText(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        text = text,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        StyledButton(
                            modifier = Modifier
                                .weight(1f)
                                .border(
                                    width = 2.dp,
                                    color = Green20,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            backgroundColor = Color.White,
                            textColor = Green20,
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            text = stringResource(id = R.string.kembali),
                            onClick = onDismiss
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        StyledButton(
                            modifier = Modifier
                                .weight(1f),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            text = stringResource(R.string.yes),
                            onClick = onConfirm
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun LogoutDialogPreview() {
    MonitoringGinjalAppTheme {
        ConfirmationDialog()
    }

}