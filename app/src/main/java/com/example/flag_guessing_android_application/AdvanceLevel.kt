package com.example.flag_guessing_android_application

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme


class AdvanceLevel : ComponentActivity() {

    val reusable = Reusable()

    var generatedImageName1 = ""
    var generatedImageName2 = ""
    var generatedImageName3 = ""

    var generatedImageKey1 = ""
    var generatedImageKey2 = ""
    var generatedImageKey3 = ""


    var randomDrawableId1 = 0
    var randomDrawableId2 = 0
    var randomDrawableId3 = 0

    var isChecked = false

    var numCount:Long = 11000

    var loseCount = 0

    var orientation = false

//    var submitted = false

    var isTextField1Enabled = true
    var isTextField2Enabled = true
    var isTextField3Enabled = true

    var submitedMain = false


    var textFeild1Colour = Color.Transparent
    var textFeild2Colour = Color.Transparent
    var textFeild3Colour = Color.Transparent

    var textBox1value = ""
    var textBox2value = ""
    var textBox3value = ""

    var result = "Wrong"

    var refreshCounter = 0

    var timesUp = false

    var marks = 0



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {

            isChecked = intent.getBooleanExtra("Timer",false)

            if(!orientation){
                randomProcess()
            }

            FlagguessingandroidapplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AdvanceLevelScreenContent()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("randomDrawableId1",randomDrawableId1)
        outState.putInt("randomDrawableId2",randomDrawableId2)
        outState.putInt("randomDrawableId3",randomDrawableId3)
        outState.putBoolean("isChecked",isChecked)
        outState.putBoolean("orientation", true)

        outState.putInt("loseCount",loseCount)


        outState.putString("textBox1value",textBox1value)
        outState.putString("textBox2value",textBox2value)
        outState.putString("textBox3value",textBox3value)


        outState.putBoolean("submitedMain", submitedMain)


        outState.putBoolean("isTextField1Enabled", isTextField1Enabled)
        outState.putBoolean("isTextField2Enabled", isTextField2Enabled)
        outState.putBoolean("isTextField3Enabled", isTextField3Enabled)



        outState.putString("generatedImageName1",generatedImageName1)
        outState.putString("generatedImageName2",generatedImageName2)
        outState.putString("generatedImageName3",generatedImageName3)

        outState.putLong("numCount",numCount)

        outState.putInt("marks",marks)





//        outState.putBoolean("orientation", true)
//        outState.putBoolean("openBoxMain", openBoxMain)
//        outState.putInt("refreshCounter",refreshCounter)
//        outState.putBoolean("isPressedMain", isPressedMain)
//        outState.putLong("numCount",numCount)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        randomDrawableId1 = savedInstanceState.getInt("randomDrawableId1",0)
        randomDrawableId2 = savedInstanceState.getInt("randomDrawableId2",0)
        randomDrawableId3 = savedInstanceState.getInt("randomDrawableId3",0)

        marks = savedInstanceState.getInt("marks",0)


        generatedImageName1 = savedInstanceState.getString("generatedImageName1","")
        generatedImageName2 = savedInstanceState.getString("generatedImageName2","")
        generatedImageName3 = savedInstanceState.getString("generatedImageName3","")

        isTextField1Enabled = savedInstanceState.getBoolean("isTextField1Enabled",true)
        isTextField2Enabled = savedInstanceState.getBoolean("isTextField2Enabled",true)
        isTextField3Enabled = savedInstanceState.getBoolean("isTextField3Enabled",true)

        textBox1value = savedInstanceState.getString("textBox1value","")
        textBox2value = savedInstanceState.getString("textBox2value","")
        textBox3value = savedInstanceState.getString("textBox3value","")

        loseCount = savedInstanceState.getInt("loseCount",0)


        numCount = savedInstanceState.getLong("numCount",11000)



        orientation = savedInstanceState.getBoolean("orientation",false)

        submitedMain = savedInstanceState.getBoolean("submitedMain",false)





    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AdvanceLevelScreenContent() {

        var endGame by remember { mutableStateOf(false) }

//        var textBox1ValueMain by remember { mutableStateOf("$textBox1value") }

        var nums:Long by remember { mutableStateOf(10) }

        var setVeiw:String by remember { mutableStateOf("⏰ OFF") }

        val cuntNum = object : CountDownTimer(numCount,1000){

            override fun onTick(millisUntilFinished: Long) {
                nums = millisUntilFinished/1000
                setVeiw = "$nums"
                numCount = nums*1000
            }

            override fun onFinish() {
                setVeiw = "Finished"

                if(!isTextField1Enabled && !isTextField2Enabled && !isTextField3Enabled){
                    result == "Correct"
                }else{
                    result == "Wrong"
                }

                endGame = !endGame
                timesUp = true

                Log.i("","Finished")

            }
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(243, 235, 221))
                    .padding(vertical = 8.dp)
            ) {
                Column (
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ){
//                    RandomImage()




                    Log.i("","oooo1 ${generatedImageName1}")
                    Log.i("","oooo2 ${generatedImageName2}")
                    Log.i("","oooo3 ${generatedImageName3}")

//                    var marks by remember { mutableStateOf(0) }


                    var textBox1 by remember { mutableStateOf(textBox1value) }
                    var textBox2 by remember { mutableStateOf(textBox2value) }
                    var textBox3 by remember { mutableStateOf(textBox3value) }


//                    var textFeild1Colour:Color
//                    var textFeild2Colour:Color
//                    var textFeild3Colour:Color

                    var haveToRefresh by remember { mutableStateOf(false) }

                    var submitted by remember { mutableStateOf(submitedMain) }


//                    var refreshCounter by remember { mutableStateOf(0) }

//                    var result by remember { mutableStateOf("") }

                    val resultColor = if (result == "Wrong") {
                        Color.Red
                    } else{
                        Color.Green
                    }

                    if(!submitted){
                        textFeild1Colour = Color.Transparent
                        textFeild2Colour = Color.Transparent
                        textFeild3Colour = Color.Transparent

                    }else{
                        if(!isTextField1Enabled){
                            textFeild1Colour = Color(165,214,167)
                        }else{
                            textFeild1Colour = Color(229,115,115)
                        }

                        if(!isTextField2Enabled){
                            textFeild2Colour = Color(165,214,167)
                        }else{
                            textFeild2Colour = Color(229,115,115)
                        }

                        textFeild3Colour = if(!isTextField3Enabled){
                            Color(165,214,167)
                        }else{
                            Color(229,115,115)
                        }
                    }

                    TopAppBar(
                        title = {
                            Text(text = "Advance Level")
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    val NavigateGuessHint = Intent(this@AdvanceLevel,MainActivity::class.java)
                                    startActivity(NavigateGuessHint)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        },
                        actions = {

                            if(isChecked == true){
                                cuntNum.start()
                                isChecked = false
                            }


                            Text(
                                text = "$setVeiw  Score $marks",
                                modifier = Modifier
                                    .padding(end = 16.dp) // Adjust the padding as needed
                            )
                        },
                    )

                    Column {
                        if(!endGame){
                            Column(
                                modifier = Modifier
                                    .weight(9f)
                                    .fillMaxWidth()

                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(horizontal = 10.dp)
                                        .background(Color(236, 226, 208)),
                                    horizontalAlignment = Alignment.CenterHorizontally

                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
//                                .fillMaxSize()
                                            .weight(1f)
                                            .border(width = 2.dp, color = Color.Black)
                                            .background(Color(17, 57, 70))
                                            .padding(10.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = randomDrawableId1),
                                            contentDescription = "Country Flag",
//                                contentScale = ContentScale.Crop
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .background(textFeild1Colour)
                                                .fillMaxWidth(),
                                            value = textBox1,
                                            onValueChange = {textBox1 = it},
                                            enabled = isTextField1Enabled,
                                            label = { Text("Enter guessing character here") },

                                            )
                                    }
                                    textBox1value = textBox1
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally

                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
//                                .fillMaxSize()
                                            .weight(1f)
                                            .border(width = 2.dp, color = Color.Black)
                                            .background(Color(17, 57, 70))
                                            .padding(10.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = randomDrawableId2),
                                            contentDescription = "Country Flag",
//                                contentScale = ContentScale.Crop
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .background(textFeild2Colour)
                                                .fillMaxWidth(),
                                            value = textBox2,
                                            onValueChange = { textBox2 = it },
                                            enabled = isTextField2Enabled,
                                            label = { Text("Enter guessing character here") },
                                        )
                                    }
                                    textBox2value = textBox2
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
//                                .fillMaxSize()
                                            .weight(1f)
                                            .border(width = 2.dp, color = Color.Black)
                                            .background(Color(17, 57, 70))
                                            .padding(10.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = randomDrawableId3),
                                            contentDescription = "Country Flag",
//                                contentScale = ContentScale.Crop
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .background(textFeild3Colour)
                                                .fillMaxWidth(),
                                            value = textBox3,
                                            onValueChange = { textBox3 = it },
                                            enabled = isTextField3Enabled,
                                            label = { Text("Enter guessing character here") },
                                        )
                                    }
                                    textBox3value = textBox3
                                }
                            }
                        }else{
                            Column(
                                modifier = Modifier
                                    .weight(9f)
                                    .fillMaxWidth()

                            ){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(2f)
                                            .fillMaxWidth()
                                            .border(
                                                2.dp,
                                                Color(17, 57, 70),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .background(
                                                Color(213, 185, 178),
                                                shape = RoundedCornerShape(10.dp)
                                            ),
                                        contentAlignment = Alignment.Center,
                                    ){
                                        Text(text = result,color = resultColor,fontSize = 30.sp)
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Column(
                                        modifier = Modifier
                                            .weight(7f)
                                            .fillMaxWidth()
                                            .border(
                                                2.dp,
                                                Color(17, 57, 70),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .background(
                                                Color.White,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .weight(0.5f)
                                                .fillMaxHeight()
                                                .fillMaxWidth()
                                                .border(
                                                    2.dp,
                                                    Color(17, 57, 70),
                                                    shape = RoundedCornerShape(10.dp)
                                                )
                                                .background(
                                                    Color(213, 185, 178),
                                                    shape = RoundedCornerShape(10.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ){
                                            Text(
                                                text = "Result",
                                                color = Color(88, 44, 77),
                                                fontSize = 20.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            )
                                        }
                                        if(isTextField3Enabled){
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(1f)
                                                    .padding(horizontal = 10.dp)

//                                        .background(Color.Red, shape = RoundedCornerShape(10.dp))
                                            ){
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight(),
                                                    verticalArrangement = Arrangement.SpaceEvenly
                                                ){
                                                    Text(
                                                        text = "First flag",
                                                        modifier = Modifier
                                                            .background(Color(236, 226, 208))
                                                            .padding(5.dp)
                                                            .fillMaxWidth()
                                                    )
                                                    Divider(thickness = 1.dp, color = Color.Black)
                                                    Text(text = "Your answer: ${textBox1}")
                                                    Text(text = "Correct answer : ${generatedImageName1}")
                                                }
                                            }
                                        }
                                        if(isTextField2Enabled){
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(1f)
                                                    .padding(horizontal = 10.dp)
//                                        .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                                            ){
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight(),
                                                    verticalArrangement = Arrangement.SpaceEvenly
                                                ){
                                                    Text(
                                                        text = "Second flag",
                                                        modifier = Modifier
                                                            .background(Color(236, 226, 208))
                                                            .padding(5.dp)
                                                            .fillMaxWidth()
                                                    )
                                                    Divider(thickness = 1.dp, color = Color.Black)
                                                    Text(text = "Your answer: ${textBox2}")
                                                    Text(text = "Correct answer : ${generatedImageName2}")
                                                }
                                            }
                                        }
                                        if(isTextField3Enabled){
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(1f)
                                                    .padding(horizontal = 10.dp)
//                                        .background(Color.Green, shape = RoundedCornerShape(10.dp))
                                            ){
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight(),
                                                    verticalArrangement = Arrangement.SpaceEvenly
                                                ){
                                                    Text(
                                                        text = "Third flag",
                                                        modifier = Modifier
                                                            .background(Color(236, 226, 208))
                                                            .padding(5.dp)
                                                            .fillMaxWidth()
                                                    )
                                                    Divider(thickness = 1.dp, color = Color.Black)
                                                    Text(text = "Your answer: ${textBox3}")
                                                    Text(text = "Correct answer : ${generatedImageName3}")
                                                }
                                            }
                                        }
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                                .padding(horizontal = 10.dp)
//                                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                                        ){
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight(),
                                                verticalArrangement = Arrangement.SpaceEvenly
                                            ){
                                                Divider(thickness = 1.dp, color = Color.Black)
                                                Text(
                                                    text = "Score",
                                                    modifier = Modifier
                                                        .background(Color(236, 226, 208))
                                                        .padding(5.dp)
                                                        .fillMaxWidth()
                                                )
                                                Text(text = "$marks")
//                                    Divider(thickness = 1.dp, color = Color.Black)
                                            }
                                        }



                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .weight(1f)
                        ) {
                            Button(
                                onClick = {

                                    if(!timesUp){
                                        submitted = true
                                        submitedMain = true

                                        Log.i("","kk $textBox1")
                                        Log.i("","new  ${textBox1value}")


                                        Log.i("","new  ${textBox1value}")



                                        marks = 0

                                        if(textBox1.uppercase() == generatedImageName1){
                                            Log.i("","First flag answer is correct")
                                            marks++
                                            isTextField1Enabled = false
                                        }else{
                                            Log.i("","First flag answer is wrong")
                                        }

                                        if(textBox2.uppercase() == generatedImageName2){
                                            Log.i("","Second flag answer is correct")
                                            marks++
                                            isTextField2Enabled = false
                                        }else{
                                            Log.i("","Second flag answer is wrong")
                                            textFeild2Colour = Color.Black
                                        }

                                        if(textBox3.uppercase() == generatedImageName3){
                                            Log.i("","Third flag answer is correct")
                                            marks++
                                            isTextField3Enabled = false
                                        }else{
                                            Log.i("","Third flag answer is wrong")
                                        }


                                        Log.i("", "Marks $marks")



                                        if(!isTextField1Enabled && !isTextField2Enabled && !isTextField3Enabled){
                                            endGame = !endGame
                                            result = "Correct"
                                            refreshCounter++
                                            submitted = false
                                            isTextField1Enabled = true
                                            isTextField2Enabled = true
                                            isTextField3Enabled = true
                                        }else{
                                            loseCount++
                                            if(loseCount >= 3){
                                                endGame = !endGame
                                                result = "Wrong"
                                                refreshCounter++
                                                submitted = false
                                                isTextField1Enabled = true
                                                isTextField2Enabled = true
                                                isTextField3Enabled = true

                                                cuntNum.cancel()
                                            }
                                        }
                                    }else{
                                        endGame = !endGame
                                        loseCount = 0
                                        refreshCounter = 2
                                        Log.i("","refresher $refreshCounter")
                                        timesUp = false
                                    }

                                },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(20),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(88, 44, 77)
                                )
                            )
                            {
                                Text(text = if (endGame) "Next" else "Submit")
                            }

                            if(refreshCounter==2){


                                setContent {
                                    numCount = 11000

                                    cuntNum.cancel()

                                    isChecked = intent.getBooleanExtra("Timer",false)

                                    submitted = false

                                    submitedMain = false

                                    loseCount = 0

                                    refreshCounter = 0

                                    orientation = false

                                    textBox1value = ""
                                    textBox2value = ""
                                    textBox3value = ""

                                    randomProcess()

                                    FlagguessingandroidapplicationTheme {
                                        // A surface container using the 'background' color from the theme
                                        Surface(
                                            modifier = Modifier.fillMaxSize(),
                                            color = MaterialTheme.colorScheme.background
                                        ) {
                                            AdvanceLevelScreenContent()
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun randomProcess(){
        val context = LocalContext.current
        val countryMap = remember { mutableMapOf<String, String>() }
        reusable.readJson(context, countryMap)

        val drawableList = listOf(

            R.drawable.ad,
            R.drawable.ae,
            R.drawable.af,
            R.drawable.ag,
            R.drawable.ai,
            R.drawable.al,
            R.drawable.am,
            R.drawable.ao,
            R.drawable.aq,
            R.drawable.ar,
//            R.drawable.`as`,
            R.drawable.at,
            R.drawable.au,
            R.drawable.aw,
            R.drawable.ax,
            R.drawable.az,
            R.drawable.ba,
            R.drawable.bb,
            R.drawable.bd,
            R.drawable.be,
            R.drawable.bf,
            R.drawable.bg,
            R.drawable.bh,
            R.drawable.bi,
            R.drawable.bj,
            R.drawable.bl,
            R.drawable.bm,
            R.drawable.bn,
            R.drawable.bo,
            R.drawable.bq,
            R.drawable.br,
            R.drawable.bs,
            R.drawable.bt,
            R.drawable.bv,
            R.drawable.bw,
            R.drawable.by,
            R.drawable.bz,
            R.drawable.ca,
            R.drawable.cc,
            R.drawable.cd,
            R.drawable.cf,
            R.drawable.cg,
            R.drawable.ch,
            R.drawable.ci,
            R.drawable.ck,
            R.drawable.cl,
            R.drawable.cm,
            R.drawable.cn,
            R.drawable.co,
            R.drawable.cr,
            R.drawable.cu,
            R.drawable.cv,
            R.drawable.cw,
            R.drawable.cx,
            R.drawable.cy,
            R.drawable.cz,
            R.drawable.de,
            R.drawable.dj,
            R.drawable.dk,
            R.drawable.dm,
            R.drawable.doo,
            R.drawable.dz,
            R.drawable.ec,
            R.drawable.ee,
            R.drawable.eg,
            R.drawable.eh,
            R.drawable.er,
            R.drawable.es,
            R.drawable.et,
            R.drawable.eu,
            R.drawable.fi,
            R.drawable.fj,
            R.drawable.fk,
            R.drawable.fm,
            R.drawable.fo,
            R.drawable.fr,
            R.drawable.ga,
            R.drawable.gbeng,
            R.drawable.gbnir,
            R.drawable.gbsct,
            R.drawable.gbwls,
            R.drawable.gb,
            R.drawable.gd,
            R.drawable.ge,
            R.drawable.gf,
            R.drawable.gg,
            R.drawable.gh,
            R.drawable.gi,
            R.drawable.gl,
            R.drawable.gm,
            R.drawable.gn,
            R.drawable.gp,
            R.drawable.gq,
            R.drawable.gr,
            R.drawable.gs,
            R.drawable.gt,
            R.drawable.gu,
            R.drawable.gw,
            R.drawable.gy,
            R.drawable.hk,
            R.drawable.hm,
            R.drawable.hn,
            R.drawable.hr,
            R.drawable.ht,
            R.drawable.hu,
            R.drawable.id,
            R.drawable.ie,
            R.drawable.il,
            R.drawable.im,
//            R.drawable.`in`,
            R.drawable.io,
            R.drawable.iq,
            R.drawable.ir,
//            R.drawable.`is`,
            R.drawable.it,
            R.drawable.je,
            R.drawable.jm,
            R.drawable.jo,
            R.drawable.jp,
            R.drawable.ke,
            R.drawable.kg,
            R.drawable.kh,
            R.drawable.ki,
            R.drawable.km,
            R.drawable.kn,
            R.drawable.kp,
            R.drawable.kr,
            R.drawable.kw,
            R.drawable.ky,
            R.drawable.kz,
            R.drawable.la,
            R.drawable.lb,
            R.drawable.lc,
            R.drawable.li,
            R.drawable.lk,
            R.drawable.lr,
            R.drawable.ls,
            R.drawable.lt,
            R.drawable.lu,
            R.drawable.lv,
            R.drawable.ly,
            R.drawable.ma,
            R.drawable.mc,
            R.drawable.md,
            R.drawable.me,
            R.drawable.mf,
            R.drawable.mg,
            R.drawable.mh,
            R.drawable.mk,
            R.drawable.ml,
            R.drawable.mm,
            R.drawable.mn,
            R.drawable.mo,
            R.drawable.mp,
            R.drawable.mq,
            R.drawable.mr,
            R.drawable.ms,
            R.drawable.mt,
            R.drawable.mu,
            R.drawable.mv,
            R.drawable.mw,
            R.drawable.mx,
            R.drawable.my,
            R.drawable.mz,
            R.drawable.na,
            R.drawable.nc,
            R.drawable.ne,
            R.drawable.nf,
            R.drawable.ng,
            R.drawable.ni,
            R.drawable.nl,
            R.drawable.no,
            R.drawable.np,
            R.drawable.nr,
            R.drawable.nu,
            R.drawable.nz,
            R.drawable.om,
            R.drawable.pa,
            R.drawable.pe,
            R.drawable.pf,
            R.drawable.pg,
            R.drawable.ph,
            R.drawable.pk,
            R.drawable.pl,
            R.drawable.pm,
            R.drawable.pn,
            R.drawable.pr,
            R.drawable.ps,
            R.drawable.pt,
            R.drawable.pw,
            R.drawable.py,
            R.drawable.qa,
            R.drawable.re,
            R.drawable.ro,
            R.drawable.rs,
            R.drawable.ru,
            R.drawable.rw,
            R.drawable.sa,
            R.drawable.sb,
            R.drawable.sc,
            R.drawable.sd,
            R.drawable.se,
            R.drawable.sg,
            R.drawable.sh,
            R.drawable.si,
            R.drawable.sj,
            R.drawable.sk,
            R.drawable.sl,
            R.drawable.sm,
            R.drawable.sn,
            R.drawable.so,
            R.drawable.sr,
            R.drawable.ss,
            R.drawable.st,
            R.drawable.sv,
            R.drawable.sx,
            R.drawable.sy,
            R.drawable.sz,
            R.drawable.tc,
            R.drawable.td,
            R.drawable.tf,
            R.drawable.tg,
            R.drawable.th,
            R.drawable.tj,
            R.drawable.tk,
            R.drawable.tl,
            R.drawable.tm,
            R.drawable.tn,
            R.drawable.to,
            R.drawable.tr,
            R.drawable.tt,
            R.drawable.tv,
            R.drawable.tw,
            R.drawable.tz,
            R.drawable.ua,
            R.drawable.ug,
            R.drawable.um,
            R.drawable.us,
            R.drawable.uy,
            R.drawable.uz,
            R.drawable.va,
            R.drawable.vc,
            R.drawable.ve,
            R.drawable.vg,
            R.drawable.vi,
            R.drawable.vn,
            R.drawable.vu,
            R.drawable.wf,
            R.drawable.ws,
            R.drawable.xk,
            R.drawable.ye,
            R.drawable.yt,
            R.drawable.za,
            R.drawable.zm,
            R.drawable.zw
        )



        randomDrawableId1 = drawableList.random()
        randomDrawableId2 = drawableList.random()
        randomDrawableId3 = drawableList.random()

        // Get the name of the generated drawable resource using reflection
        val drawableName1: String = try {
            val fieldName = context.resources.getResourceEntryName(randomDrawableId1)
            "R.drawable.$fieldName"
        } catch (e: Resources.NotFoundException) {
            "Unknown"
        }

        val drawableName2: String = try {
            val fieldName = context.resources.getResourceEntryName(randomDrawableId2)
            "R.drawable.$fieldName"
        } catch (e: Resources.NotFoundException) {
            "Unknown"
        }

        val drawableName3: String = try {
            val fieldName = context.resources.getResourceEntryName(randomDrawableId3)
            "R.drawable.$fieldName"
        } catch (e: Resources.NotFoundException) {
            "Unknown"
        }

        generatedImageKey1 = drawableName1.substring(11).uppercase()
        generatedImageName1 = countryMap[generatedImageKey1.uppercase()].toString().uppercase()

        generatedImageKey2 = drawableName2.substring(11).uppercase()
        generatedImageName2 = countryMap[generatedImageKey2.uppercase()].toString().uppercase()

        generatedImageKey3 = drawableName3.substring(11).uppercase()
        generatedImageName3 = countryMap[generatedImageKey3.uppercase()].toString().uppercase()
    }


}




