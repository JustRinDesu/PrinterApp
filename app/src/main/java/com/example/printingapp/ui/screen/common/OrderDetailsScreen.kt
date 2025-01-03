package com.example.printingapp.ui.screen.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printingapp.model.Order
import com.example.printingapp.model.PrintDetail
import com.example.printingapp.ui.ErrorScreen
import com.example.printingapp.ui.LoadingScreen
import com.example.printingapp.ui.theme.PrintingAppTheme


@Composable
fun OrderDetailsScreen(
    modifier: Modifier = Modifier,
    orderId: String,
    viewModel: OrderDetailsViewModel? = viewModel(factory = OrderDetailsViewModel.Factory),
) {

    viewModel?.let{

        LaunchedEffect(Unit) {
            viewModel.getOrder(orderId)
        }

        val orderUiState = viewModel.orderUiState

        when (orderUiState) {
            is OrderDetailsViewModel.OrderUiState.Loading -> {
                LoadingScreen(modifier = modifier.fillMaxSize())
            }

            is OrderDetailsViewModel.OrderUiState.Success -> {
                OrderDetails(orderUiState.order)
            }

            is OrderDetailsViewModel.OrderUiState.Error -> {
                orderUiState.exception?.message?.let {
                    Text(it)
                    ErrorScreen(
                        retryAction = {viewModel.getOrder(orderId)}
                    )
                }
            }

            else -> {}
        }

    }


}

@Composable
fun OrderDetails(
    order: Order
){
    Column(
        modifier = Modifier.padding(horizontal = 2.dp)
    ){
        Text(
            text = order.order_name ?: "No Document Name",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = order.status
        )
        Text(
            text = order.location
        )
        Text(
            text = order.orderDate
        )
        Text(
            text = order.print_detail.paper_type
        )
        Text(
            text = "${order.print_detail.paper_width} x ${order.print_detail.paper_height}"
        )
        Text(
            text = "Colored:" + when{
                (order.print_detail.is_color == 1) ->  "Yes"
                else -> "No"
            }
        )

        val context = LocalContext.current

        Button(
            onClick = {
                openFile(
                    "http://157.245.204.192:3000/download/${order.print_detail.file_id}",
                    context = context
                )
            }
        )
        {
            Text("Open File")
        }





    }
}

fun openFile(
    uri: String,
    context: Context
){
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(browserIntent)

}

val orderSample: Order = Order(
    order_id = "id_rjqw1l87r",
    order_name = "Test Document",
    location = "123 Main St",
    status = "pending",
    customer_id = "customer_1",
    admin_id = null,
    orderDate = "2024-12-12",
    finishedDate = null,
    print_detail = PrintDetail(
        print_detail_id = "id_rkusze03k",
        no_of_copy = 1,
        paper_type = "Glossy",
        paper_width = 11.69,
        paper_height = 11.69,
        is_color = 0,
        file_id = "file-01"
    )
)

@Preview(showBackground = true)
@Composable
fun OrderDetailsScreenPreview(){
    PrintingAppTheme {
        OrderDetails(order = orderSample)
    }
}