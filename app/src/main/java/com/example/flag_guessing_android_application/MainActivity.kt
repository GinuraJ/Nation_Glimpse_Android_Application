package com.example.flag_guessing_android_application

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme

class MainActivity : ComponentActivity() {

    var isCheckedMain = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            var isChecked by remember { mutableStateOf(isCheckedMain) }

            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .weight(1.5f),
                ){
//                    Image(
//                        painter = painterResource (id = R.drawable.worldmap3),
//                        contentDescription = "Background image",
//                        contentScale = ContentScale.FillBounds,
//                        modifier = Modifier.matchParentSize()
//                    )

                    Column(
                        modifier = Modifier
//                            .weight(1.5f)
//                            .background(Color.Blue)
                            .fillMaxHeight()
//                            .background(Color.White)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(

                            text = "  \uD835\uDCDD\uD835\uDCEA\uD835\uDCFD\uD835\uDCF2\uD835\uDCF8\uD835\uDCF7 \n     \uD835\uDCD6\uD835\uDCF5\uD835\uDCF2\uD835\uDCF6\uD835\uDCF9\uD835\uDCFC\uD835\uDCEE",
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
//                                .padding(10.dp)
                                .background(Color(12, 45, 72))
                                
//                                .background(Color.Black)
                                .fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Switch(
                                checked = isChecked,
                                onCheckedChange = { newCheckedState ->
                                    isChecked = newCheckedState
                                    isCheckedMain = !isCheckedMain
                                    // Handle the new state as needed
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    uncheckedThumbColor = Color.Black
                                ),
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(12, 45, 72))
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = 4.dp)
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = {
                                val NavigateToGeussCountry = Intent(this@MainActivity,GuessCountry::class.java)
                                NavigateToGeussCountry.putExtra("Timer",isChecked)
                                startActivity(NavigateToGeussCountry)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(0.7f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(17,190,121)
                            )
                        )
                        {
                            Text(text = "Guess the country")
                        }
                        Button(
                            onClick = {
                                val NavigateGuessHint = Intent(this@MainActivity,GuessHint::class.java)
                                NavigateGuessHint.putExtra("Timer",isChecked)
                                startActivity(NavigateGuessHint)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(0.7f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(136,225,241)
                            )
                        ) {
                            Text(text = "Guess hint")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = 4.dp)
                            .padding(horizontal = 8.dp), // Set 8dp padding for left and right borders
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Top

                    ) {
                        Button(
                            onClick = {
                                val NavigateGuessTheFlag = Intent(this@MainActivity,GuessTheFlag::class.java)
                                NavigateGuessTheFlag.putExtra("Timer",isChecked)
                                startActivity(NavigateGuessTheFlag)
                            },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(0.7f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(242,212,92)
                            )

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
                                .fillMaxHeight(0.7f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(160, 32, 240)
                            )

                        ) {
                            Text(text = "Advance level")
                        }
                    }
                }

            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isCheckedMain", isCheckedMain)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isCheckedMain = savedInstanceState.getBoolean("isCheckedMain",false)

    }

}
