package com.example.printingapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.printingapp.ui.theme.PrintingAppTheme
import com.example.printingapp.ui.theme.backgroundDark

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
        var preset by remember { mutableStateOf("") }
        var paperType by remember { mutableStateOf("") }
        var paperSize by remember { mutableStateOf("") }
        var paperWidth by remember { mutableStateOf("") }
        var paperHeight by remember { mutableStateOf("") }
        var isColorPrint by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.Top

        ) {

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    label = { Text("Document Name") },
                    value = documentName,
                    singleLine = true,
                    onValueChange = { documentName = it },
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                )

                /* TODO location choice*/
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f).padding(top=7.dp).padding(start = 20.dp),
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

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                /* TODO Replace with dropdown*/
                OutlinedTextField(
                    label = { Text("Preset") },
                    value = preset,
                    onValueChange = { preset = it },
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                )
                OutlinedTextField(
                    label = { Text("No of page") },
                    value = noOfPage,
                    onValueChange = { noOfPage = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                /* TODO Replace with dropdown*/
                OutlinedTextField(
                    label = { Text("Paper type") },
                    value = paperType,
                    onValueChange = { paperType = it },
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(vertical = 2.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp),

                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                /* TODO Replace with dropdown*/
                OutlinedTextField(
                    label = { Text("A4") },
                    value = paperSize,
                    onValueChange = { paperSize = it },
                    modifier = Modifier
                        .weight(3f)

                )
                OutlinedTextField(
                    label = { Text("Width") },
                    value = paperWidth,
                    onValueChange = { paperWidth = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(2f)
                )
                OutlinedTextField(
                    label = { Text("Height") },
                    value = paperHeight,
                    onValueChange = { paperHeight = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .weight(2f)
                )
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp),

                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                
            ){

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

            Button(
                onClick = {
                    /*TODO Store order logic*/
                    onBackButton()
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