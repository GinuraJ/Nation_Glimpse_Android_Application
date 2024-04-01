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
import androidx.compose.ui.text.style.TextAlign
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
                    .fillMaxSize()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .weight(1.5f),
                ){
                    Image(
                        painter = painterResource (id = R.drawable.logo),
                        contentDescription = "Background image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.matchParentSize()
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {


                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Switch(
                                checked = isChecked,
                                onCheckedChange = { newCheckedState ->
                                    isChecked = newCheckedState
                                    isCheckedMain = !isCheckedMain
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    uncheckedThumbColor = Color.Black,
                                ),
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(255, 204, 153))
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
//                                containerColor = Color(17,190,121)
                                containerColor = Color(255, 99, 99)

                            )
                        )
                        {
                            Text(
                                text = "Guess the country",
                                color = Color(19, 56, 135),
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
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
//                                containerColor = Color(136,225,241)
                                containerColor = Color(158, 157, 157)


                            )
                        ) {
                            Text(
                                text = "Guess hint",
                                color = Color(19, 56, 135),
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
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
//                                containerColor = Color(242,212,92)
                                containerColor = Color(162, 103, 105)

                            )

                        ) {
                            Text(
                                text = "Guess the flag",
                                color = Color.Black,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(
                            onClick = {
                                val NavigateToGeussCountry = Intent(this@MainActivity,AdvanceLevel::class.java)
                                NavigateToGeussCountry.putExtra("Timer",isChecked)
                                startActivity(NavigateToGeussCountry)
                            },

                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(0.7f),
                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color(160, 32, 240)
                                containerColor = Color(139, 93, 163)

                            )

                        ) {
                            Text(
                                text = "Advance level",
                                color = Color.Black,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold)
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
