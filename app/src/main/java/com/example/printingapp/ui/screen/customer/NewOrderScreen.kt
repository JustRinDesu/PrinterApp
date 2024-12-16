package com.example.printingapp.ui.screen.customer

import OrdersUiState
import OrdersViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printingapp.model.Order
import com.example.printingapp.model.PrintDetail
import com.example.printingapp.ui.DropDown
import com.example.printingapp.ui.ErrorScreen
import com.example.printingapp.ui.LoadingScreen
import com.example.printingapp.ui.theme.PrintingAppTheme

val paperSizes = listOf(
    "Custom" to ("8.27" to "11.69"),
    "A4" to ("8.27" to "11.69"),
    "A3" to ("11.69" to "16.54"),
    "Letter" to ("8.5" to "11.0")
)

@Composable
fun NewOrderScreen(
    modifier: Modifier = Modifier,
    onBackButton: () -> Unit = {}
) {

    Surface(
        modifier = modifier,
    ) {
        var documentName by remember { mutableStateOf("") }
        var noOfPage by remember { mutableStateOf("") }
        val paperType = listOf("Matte", "Gloss", "Cardstock")
        val paperTypeIndex = remember { mutableStateOf(0) }
        val paperSizeIndex = remember { mutableStateOf(0) }
        var paperWidth by remember { mutableStateOf("") }
        var paperHeight by remember { mutableStateOf("") }
        var isColorPrint by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.Center

        ) {

            CustomInputRow {
                OutlinedTextField(
                    label = { Text("Document Name") },
                    value = documentName,
                    singleLine = true,
                    onValueChange = { documentName = it },
                    modifier = Modifier
                        .weight(3f)
                )

                /* TODO location choice*/
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp),
                    onClick = {},
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(0.dp),

                    ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location"
                    )
                }
            }

            CustomInputRow {
                OutlinedTextField(
                    label = { Text("No of copy") },
                    value = noOfPage,
                    onValueChange = { noOfPage = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                )
            }

            CustomInputRow {
                DropDown(
                    paperType,
                    paperTypeIndex,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 4.dp),
                    label = {
                        Text("Paper type", fontSize = 10.sp)
                    }
                )
            }

            CustomInputRow {

                DropDown(
                    paperSizes.map { it.first },
                    paperSizeIndex,
                    modifier = Modifier
                        .weight(7f)
                        .padding(top = 4.dp),
                    label = {
                        Text("Size", fontSize = 10.sp)
                    }
                )
                OutlinedTextField(
                    label = { Text("Width") },
                    value = paperWidth,
                    onValueChange = { paperWidth = it },
                    readOnly = (paperSizeIndex.value != 0),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(5f)
                )
                OutlinedTextField(
                    label = { Text("Height") },
                    value = paperHeight,
                    onValueChange = { paperHeight = it },
                    readOnly = (paperSizeIndex.value != 0),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(5f)
                )
            }

            LaunchedEffect(paperSizeIndex.value) {
                paperWidth = paperSizes[paperSizeIndex.value].second.second
                paperHeight = paperSizes[paperSizeIndex.value].second.second
            }

            CustomInputRow {

                Text(
                    "Color Print",
                    fontSize = 19.sp
                )

                Switch(
                    checked = isColorPrint,
                    onCheckedChange = {
                        isColorPrint = it
                    },
                    thumbContent = if (isColorPrint) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    }
                )
            }


            /* TODO File upload logic*/

            var showDialog by remember { mutableStateOf(false) }

            val orderViewModel: OrdersViewModel = viewModel(factory = OrdersViewModel.Factory)

            if (showDialog) {
                MinimalDialog( { onBackButton() },orderViewModel.ordersUiState)
            }

            Button(
                onClick = {
                    createNewOrder(
                        documentName = documentName,
                        noOfPage = noOfPage,
                        paperType = paperType,
                        paperTypeIndex = paperTypeIndex,
                        paperWidth = paperWidth,
                        paperHeight = paperHeight,
                        isColorPrint = isColorPrint,
                        orderViewModel = orderViewModel
                    )
                    showDialog = true
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )

            ) {
                Text("Place Order")
            }
        }


    }

}

private fun createNewOrder(
    documentName: String,
    noOfPage: String,
    paperType: List<String>,
    paperTypeIndex: MutableState<Int>,
    paperWidth: String,
    paperHeight: String,
    isColorPrint: Boolean,
    orderViewModel: OrdersViewModel
) {
    val order = Order(
        order_name = documentName,
        order_id = "",
        location = "123 Main St",
        status = "pending",
        customer_id = "customer_1",
        admin_id = "",
        orderDate = "2024-12-12",
        finishedDate = "",
        print_detail = PrintDetail(
            print_detail_id = "",
            no_of_copy = noOfPage.toInt(),
            paper_type = paperType[paperTypeIndex.value],
            paper_width = paperWidth.toDouble(),
            paper_height = paperHeight.toDouble(),
            is_color = if (isColorPrint) 1 else 0,
            file_id = "file-01"
        )
    )
    orderViewModel.createOrder(order)

}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit, uiState: OrdersUiState) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {

            when (uiState) {
                is OrdersUiState.Loading -> {
                    LoadingScreen()
                }
                is OrdersUiState.OrderModificationSuccess -> {
                    Text(
                        text = uiState.response.message().toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                is OrdersUiState.Error -> {
                    uiState.exception?.message?.let {
                        Text(it)
                        ErrorScreen(
                            retryAction = onDismissRequest
                        )
                    }
                }
                else -> {}
            }

            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun CustomInputRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(60.dp),

        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun NewOrderPreviewDark() {
    PrintingAppTheme(darkTheme = true) {
        NewOrderScreen()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun NewOrderPreview() {
    PrintingAppTheme() {
        NewOrderScreen()
    }
}