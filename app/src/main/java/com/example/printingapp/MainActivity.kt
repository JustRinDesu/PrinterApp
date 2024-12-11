package com.example.printingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.printingapp.ui.theme.PrintingAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.printingapp.ui.AdminDashboardScreen
import com.example.printingapp.ui.CustomerDashboardScreen
import com.example.printingapp.ui.LoginScreen
import com.example.printingapp.ui.NewOrderScreen

enum class PrinterAppScreen() {
    Login,
    CustomerDashboard,
    AdminDashboard,
    NewOrder,
    OrderHistory,
    OrderDetails,
    OrderList,
    JobList,
    PickupList
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrintingAppTheme {

                PrinterApp()
            }
        }
    }


}

@Composable
private fun PrinterApp() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PrinterAppScreen.Login.name,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(route = PrinterAppScreen.Login.name) {
                LoginScreen(onLoginSuccess = { userRole ->
                    if (userRole == "customer") {
                        navController.navigate(
                            PrinterAppScreen.CustomerDashboard.name
                        )
                    }
                    if (userRole == "admin") {
                        navController.navigate(
                            PrinterAppScreen.AdminDashboard.name
                        )
                    }

                })
            }


            composable(PrinterAppScreen.CustomerDashboard.name) {
                CustomerDashboardScreen(
                    onNewOrderButton = { navController.navigate(PrinterAppScreen.NewOrder.name) },
                    onOrderHistoryButton = { navController.navigate(PrinterAppScreen.OrderHistory.name) },
                    onExtraButton = {}
                )
            }
            composable(PrinterAppScreen.NewOrder.name) {
                NewOrderScreen(onBackButton = { navController.popBackStack() })
            }
            composable(PrinterAppScreen.OrderHistory.name) {
                /*TODO OrderHistory*/
            }




            composable(PrinterAppScreen.AdminDashboard.name) {
                AdminDashboardScreen()
            }
            composable(PrinterAppScreen.JobList.name) {
                /*TODO JobList*/
            }
            composable(PrinterAppScreen.PickupList.name) {
                /*TODO PickupList*/
            }
            composable(PrinterAppScreen.OrderList.name) {
                /*TODO OrderList*/
            }


            composable(PrinterAppScreen.OrderDetails.name) {
                /*TODO OrderDetails*/
            }

        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PrinterAppPreview() {
    PrintingAppTheme {
        PrinterApp()
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun LoginPreview() {
//    PrintingAppTheme {
//        LoginScreen()
//    }
//}
//
//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun CustomerDashboardPreview() {
//    PrintingAppTheme {
//        CustomerDashboardScreen()
//    }
//}
//
//@Preview(showBackground = true, widthDp = 360, heightDp = 640)
//@Composable
//fun AdminDashboardPreview() {
//    PrintingAppTheme {
//        AdminDashboardScreen()
//    }
//}