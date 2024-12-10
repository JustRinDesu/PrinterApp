package com.example.printingapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.printingapp.ui.theme.PrintingAppTheme

@Composable
fun CustomerDashboardScreen(
    modifier: Modifier = Modifier,
    onNewOrderButton: () -> Unit = {},
    onOrderHistoryButton: () -> Unit = {},
    onExtraButton: () -> Unit = {},
) {
    Surface(
        modifier = modifier.padding(0.dp, 80.dp, 0.dp, 0.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                CircleButtonDashboard("New Order", modifier = Modifier.weight(1f), onButtonClick = onNewOrderButton)
                CircleButtonDashboard("Order History", modifier = Modifier.weight(1f), onButtonClick = onOrderHistoryButton)
                CircleButtonDashboard("Extra", modifier = Modifier.weight(1f), onButtonClick = onExtraButton)


            }
            Text(
                text = "Active order:",
                modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 0.dp)
            )
            OrderCard("Document A", "Size A4, 24 copies...", "Preparing")
            OrderCard("Document B", "Size A3, 1 copies...", "Ready to Pickup")
        }
    }
}

@Composable
private fun OrderCard(orderName: String, orderDescription: String, orderStatus: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
            .background(Color(201, 217, 242))
    ) {

        Text(
            text = orderName,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp),
        )
        Text(
            text = orderDescription,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
            fontSize = 12.sp
        )
        Text(
            text = orderStatus,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun CustomerDashboardPreview() {
    PrintingAppTheme {
        CustomerDashboardScreen()
    }
}