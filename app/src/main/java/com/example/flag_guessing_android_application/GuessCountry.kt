package com.example.flag_guessing_android_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme

class GuessCountry : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlagguessingandroidapplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessCountryScreenContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessCountryScreenContent() {
    var text by remember { mutableStateOf("Hello Android!") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {
        // TopAppBar for navigation
        TopAppBar(
            title = {
                Text(text = "Guess Country")
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                // Add additional actions here
            }
        )

        // Your screen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = {
                    // Example: Change the text on button click
                    text = "Hello Compose!"
                }
            ) {
                Text(text = "Change Text")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuessCountryScreenPreview() {
    FlagguessingandroidapplicationTheme {
        GuessCountryScreenContent()
    }
}
