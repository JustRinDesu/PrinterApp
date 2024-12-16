@file:Suppress("CanBeVal")

package com.example.printingapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.printingapp.ui.theme.PrintingAppTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginSuccess: (String) -> Unit = {}) {
    Surface(
        modifier = modifier
    ) {
        var usernameInput by remember { mutableStateOf("") }
        var passwordInput by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text("username")
            TextField(
                value = usernameInput,
                onValueChange = { usernameInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            Text("password")
            TextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            Button(
                onClick = {
                    /* TODO Login logic */
                    var userRole = "customer"
                    if (usernameInput == "admin") {
                        userRole = "admin"
                    }

                    onLoginSuccess(userRole)
                }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )

            ) {
                Text("Login")
            }
        }


    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun LoginPreview() {
    PrintingAppTheme {
        LoginScreen()
    }
}