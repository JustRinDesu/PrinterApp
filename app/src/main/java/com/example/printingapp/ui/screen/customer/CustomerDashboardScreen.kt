package com.example.printingapp.ui.screen.customer

import OrdersViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printingapp.model.Order
import com.example.printingapp.ui.CircleButtonDashboard
import com.example.printingapp.ui.ErrorScreen
import com.example.printingapp.ui.LoadingScreen


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

        val orderViewModel: OrdersViewModel = viewModel(factory = OrdersViewModel.Factory)
        LaunchedEffect(Unit) {
            orderViewModel.getAllOrders()
        }

        Column(
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                CircleButtonDashboard(
                    "New Order",
                    modifier = Modifier.weight(1f),
                    onButtonClick = onNewOrderButton
                )
                CircleButtonDashboard(
                    "Order History",
                    modifier = Modifier.weight(1f),
                    onButtonClick = onOrderHistoryButton
                )
                CircleButtonDashboard(
                    "Extra",
                    modifier = Modifier.weight(1f),
                    onButtonClick = onExtraButton
                )


            }
            Text(
                text = "Active order:",
                modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 0.dp)
            )

            MyActiveOrder(
                ordersUiState = orderViewModel.ordersUiState,
                retryAction = orderViewModel::getAllOrders,
            )
        }
    }
}

@Composable
fun MyActiveOrder(
    ordersUiState: OrdersViewModel.OrdersUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (ordersUiState) {
        is OrdersViewModel.OrdersUiState.Loading -> {
            LoadingScreen(modifier = modifier.fillMaxSize())
        }

        is OrdersViewModel.OrdersUiState.Success -> {
            OrderCards(ordersUiState.orders)
        }

        is OrdersViewModel.OrdersUiState.Error -> {
            ordersUiState.exception?.message?.let {
                Text(it)
                ErrorScreen(
                    retryAction = retryAction
                )
            }
        }

        else -> {}
    }
}

@Composable
fun OrderCards(orders: List<Order>) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .verticalScroll(rememberScrollState())
    ) {
        orders.forEach {
            OrderCard(it)
        }
    }
}

@Composable
private fun OrderCard(order: Order) {

    val orderDescription =
        "${order.print_detail.paper_type} - ${order.print_detail.paper_width}x${order.print_detail.paper_height}"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {

        Text(
            text = order.order_name ?: "Test",
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
            text = order.status,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}
