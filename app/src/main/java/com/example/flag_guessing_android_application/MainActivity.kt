package com.example.flag_guessing_android_application

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .weight(1.5f)
                        .background(Color.Blue)
                        .fillMaxWidth()
                ) {
                    Text(text = "Topic")
                    Text(text = "Topic")
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Green)
                        .fillMaxWidth(),
//                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.Center

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                val NavigateToGeussCountry = Intent(this@MainActivity,GuessCountry::class.java)
                                startActivity(NavigateToGeussCountry)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                        )
                        {
                            Text(text = "Guess the country")
                        }
                        Button(
                            onClick = {
                                val NavigateGuessHint = Intent(this@MainActivity,GuessHint::class.java)
                                startActivity(NavigateGuessHint)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                        ) {
                            Text(text = "Guess hint")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp), // Set 8dp padding for left and right borders
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                val NavigateGuessHint = Intent(this@MainActivity,GuessTheFlag::class.java)
                                startActivity(NavigateGuessHint)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                        ) {
                            Text(text = "Guess the flag")
                        }
                        Button(
                            onClick = {
                                val NavigateGuessHint = Intent(this@MainActivity, AdvanceLevel::class.java)
                                startActivity(NavigateGuessHint)
                                      },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)

                        ) {
                            Text(text = "Advance level")
                        }
                    }
                }

            }

//            FlagguessingandroidapplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }


        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlagguessingandroidapplicationTheme {
        Greeting("Android")
    }
}