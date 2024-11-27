package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green5
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.MonitoringGinjalAppTheme
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.White90
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.component.StyledButton

@Composable
fun StartHemodialisaDialog(
    isDialogShown:Boolean = false,
    onDismiss:() -> Unit = {},
    onNavigateToStartHemodialisa:(HemodialisaType) -> Unit = {},
) {
    if (isDialogShown){
        val columnModifier = remember {
            Modifier
                .background(White90, shape = RoundedCornerShape(20.dp))
                .border(color = Green5, width = 2.dp, shape = RoundedCornerShape(20.dp))
                .padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 34.dp)
        }
        Dialog(onDismissRequest = onDismiss) {
            Column(
                modifier = columnModifier
            ){
                IconButton(
                    modifier = Modifier
                        .align(Alignment.End),
                    onClick = onDismiss
                ) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp),
                        imageVector = Icons.Default.Close,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 9.dp)
                ) {
                    StyledButton(
                        modifier = Modifier
                            .weight(1f),
                        backgroundColor = Green5,
                        textColor = White90,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
                        fontSize = 12.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Medium,
                        text = stringResource(R.string.hemodialisa_1_3_hari),
                        onClick = {
                            onNavigateToStartHemodialisa(
                                HemodialisaType.HEMODIALISA_1
                            )
                        }
                    )

                    Spacer(Modifier.widthIn(min = 8.dp, max = 36.dp))

                    StyledButton(
                        modifier = Modifier
                            .weight(1f),
                        backgroundColor = Green5,
                        textColor = White90,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
                        fontSize = 12.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Medium,
                        text = stringResource(R.string.hemodialisa_2_4_hari),
                        onClick = {
                            onNavigateToStartHemodialisa(
                                HemodialisaType.HEMODIALISA_2
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StartHemodialisaDialogPreview() {
    MonitoringGinjalAppTheme {
        StartHemodialisaDialog(isDialogShown = true)
    }

}