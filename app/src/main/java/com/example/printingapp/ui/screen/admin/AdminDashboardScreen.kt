package com.example.printingapp.ui.screen.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.printingapp.ui.CircleButtonDashboard
import com.example.printingapp.ui.theme.PrintingAppTheme

@Composable
fun AdminDashboardScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(0.dp, 80.dp, 0.dp, 0.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                CircleButtonDashboard("Order List", modifier = Modifier.weight(1f))
                CircleButtonDashboard("Job List", modifier = Modifier.weight(1f))
                CircleButtonDashboard("Pick up List", modifier = Modifier.weight(1f)) { }

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