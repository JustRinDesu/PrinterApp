package com.example.printingapp.ui.screen.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.printingapp.ui.CircleButtonDashboard
import com.example.printingapp.ui.ErrorScreen
import com.example.printingapp.ui.LoadingScreen
import com.example.printingapp.ui.theme.PrintingAppTheme

@Composable
fun AdminDashboardScreen(
    modifier: Modifier = Modifier,
    onOrderListClick: () -> Unit = {},
    onJobListClick: () -> Unit = {},
    onPickupListClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier.padding(0.dp, 80.dp, 0.dp, 0.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                CircleButtonDashboard("Order List", modifier = Modifier.weight(1f), onButtonClick = onOrderListClick)
                CircleButtonDashboard("Job List", modifier = Modifier.weight(1f), onButtonClick = onJobListClick)
                CircleButtonDashboard("Pick up List", modifier = Modifier.weight(1f), onButtonClick = onPickupListClick)

            }
        }
    }
}
@Preview(showBackground = true,widthDp = 360, heightDp = 640)
@Composable
fun AdminDashboardPreview() {
    PrintingAppTheme {
        AdminDashboardScreen()
    }
}

