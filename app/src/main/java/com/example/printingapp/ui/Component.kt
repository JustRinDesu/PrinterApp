package com.example.printingapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleButtonDashboard(
    label: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
){
    Column(
        modifier = modifier
            .padding(10.dp)
            .width(110.dp)
    ) {
        OutlinedButton(
            onClick = onButtonClick,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
        ) {

        }
        Text(
            text = label,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

    }
}