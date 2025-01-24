package com.example.printingapp.ui.screen.admin

import androidx.activity.compose.rememberLauncherForActivityResult
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
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun AdminDashboardScreen(
    modifier: Modifier = Modifier,
    onOrderListClick: () -> Unit = {},
    onJobListClick: () -> Unit = {},
    onPickupListClick: () -> Unit = {},
    onOrderClick: (String) -> Unit = {},
    onAllOrderListClick: () -> Unit = {}
) {

    val qrScannerLauncher = rememberLauncherForActivityResult(
        contract = ScanContract()
    ) { result ->
        if (result.contents != null) {
            onOrderClick(result.contents)
        }
    }

    Surface(
        modifier = modifier.padding(20.dp, 80.dp, 20.dp, 0.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                CircleButtonDashboard("Order List", modifier = Modifier.weight(1f), onButtonClick = onOrderListClick)
                CircleButtonDashboard("Job List", modifier = Modifier.weight(1f), onButtonClick = onJobListClick)
                CircleButtonDashboard("Pickup List", modifier = Modifier.weight(1f), onButtonClick = onPickupListClick)

            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CircleButtonDashboard("All Order", modifier = Modifier.weight(1f), onButtonClick = onAllOrderListClick)
                CircleButtonDashboard("Scan QR", modifier = Modifier.weight(1f), onButtonClick = {
                    val options = ScanOptions()
                    options.setPrompt("Scan a QR Code")
                    options.setBeepEnabled(true)
                    options.setBarcodeImageEnabled(true)
                    qrScannerLauncher.launch(options)
                })

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

