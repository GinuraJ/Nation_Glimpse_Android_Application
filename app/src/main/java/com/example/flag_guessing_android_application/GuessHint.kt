package com.example.flag_guessing_android_application

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class GuessHint : ComponentActivity() {

    val mutableList: MutableList<String> = mutableListOf()

    var generatedImageKey = ""

    var generatedImageName = ""

    var displayText = ""

    var count = 1

    var looseCount = 0

    var chooseCorrect = 0

    var countryNameGuessing = ""

    var isChecked = false

    var generatedImageDrawableId = 0


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        
        setContent {

            RandomImageProcess()

            isChecked = intent.getBooleanExtra("Timer",false)


            FlagguessingandroidapplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessHintScreenContent()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GuessHintScreenContent() {

        var isPressed by remember { mutableStateOf(false) }

        var loseOrWin = remember { mutableStateOf(false) }

        var text by remember { mutableStateOf("") }

        var refreshCounter by remember { mutableStateOf(0) }

        var nums:Long by remember { mutableStateOf(10) }

        var setVeiw:String by remember { mutableStateOf("⏰ OFF") }

        val cuntNum = object : CountDownTimer(11000,1000){

            override fun onTick(millisUntilFinished: Long) {
                nums = millisUntilFinished/1000
                setVeiw = "$nums"
            }

            override fun onFinish() {

                setVeiw = "Finished"

                var chosedCorrect = 0

                for (index in generatedImageName.indices) {

                    val char = generatedImageName[index].toString()

                    if (char == text.uppercase()) {

                        countryNameGuessing = "$char $text"

                        mutableList[index] = char

                        displayText = ""

                        for (index in 0 until minOf(generatedImageName.length, mutableList.size)) {

                            displayText = displayText + " ${mutableList[index]} "
                        }

                        countryNameGuessing = displayText

                        chosedCorrect = 1
                    }
                }

                looseCount = 3

                if(looseCount >= 3){
                    loseOrWin.value = !loseOrWin.value
                    isPressed = !isPressed
                    refreshCounter++
//                        mutableList.clear()
                }

                var emptyFound = 0
                for(element in mutableList){
                    if(element == " _ "){
                        emptyFound++
                    }
                }
                if(emptyFound == 0){
                    loseOrWin.value = !loseOrWin.value
                    isPressed = !isPressed
                    chooseCorrect = 1
                    refreshCounter++
//                        mutableList.clear()

                }

                if(refreshCounter > 1){
                    mutableList.clear()
                    count = 1
                    looseCount = 0
                    chooseCorrect = 0
                    displayText = ""

                    setContent {
                        RandomImageProcess()

                        refreshCounter = 0
                        isChecked = intent.getBooleanExtra("Timer",false)

                        FlagguessingandroidapplicationTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                GuessHintScreenContent()
                            }
                        }
                    }
                }


                text = ""

            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // TopAppBar for navigation
            TopAppBar(
                title = {
                    Text(text = "Guess Hint")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val NavigateGuessHint = Intent(this@GuessHint,MainActivity::class.java)
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
                        text = "$setVeiw",
                        modifier = Modifier
                            .padding(10.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color =  Color(17, 57, 70)
                    )
                },
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(204, 211, 202))
                    .padding(vertical = 8.dp)
            ) {
                Column (
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(width = 2.dp, color = Color.Black)
                            .background(Color(17, 57, 70))
                            .padding(10.dp)

                    ){
                        Image(
                            painter = painterResource(id = generatedImageDrawableId),
//                painter = painterResource(id = resources.getIdentifier(testImage, "drawable", packageName)),
                            contentDescription = "Country Flag",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .weight(2f)
                ){
//                    guessHint()


                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(5f)
                                .padding(vertical = 10.dp)
                                .fillMaxWidth(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(2f)
//                        .background(Color.Blue)
                                    .border(5.dp, Color(5, 92, 157))
                                    .fillMaxWidth(),
                            ) {

                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            if(!loseOrWin.value){
                                Box(
                                    modifier = Modifier
                                        .weight(1.5f)
//                            .background(Color(255, 0, 124))
                                        .background(Color(136,225,241),shape = RoundedCornerShape(30))
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                    ) {
                                        Text(text = generatedImageName)
                                        Text(text = countryNameGuessing, color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "$displayText")
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1.5f)
//                        .background(Color.Yellow)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .fillMaxWidth(),
                                        value = text,
                                        onValueChange = { text = it },
                                        label = { Text("Enter guessing character here") },
                                    )
                                }
                            }else{
                                var boxColor = Color(174, 214, 241)
                                var textColour = Color(174, 214, 241)
                                var correctOrWrongMsg = ""

                                if(looseCount==3){
                                    textColour = Color(215, 0, 0)
                                    correctOrWrongMsg = "Wrong"
                                }
                                if(chooseCorrect == 1){
                                    textColour = Color(0, 215, 0)
                                    correctOrWrongMsg = "Correct"
                                }


                                Box(
                                    modifier = Modifier
                                        .weight(3f)
                                        .background(Color.Gray)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ){

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = correctOrWrongMsg,
                                            modifier = Modifier.padding(vertical = 10.dp),
                                            fontSize = 20.sp,
                                            color = textColour,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = generatedImageName,
                                            modifier = Modifier.padding(vertical = 10.dp),
                                            fontSize = 20.sp,
                                            color = Color(36, 113, 163),
                                        )
                                    }
                                }
                            }
                        }
                        Button(
                            onClick = {
                                var chosedCorrect = 0
                                for (index in generatedImageName.indices) {

                                    val char = generatedImageName[index].toString()

                                    if (char == "${text.uppercase()}") {

                                        countryNameGuessing = "$char $text"

                                        mutableList[index] = char

                                        displayText = ""

                                        for (index in 0 until minOf(generatedImageName.length, mutableList.size)) {

                                            displayText = displayText + " ${mutableList[index]} "
                                        }

                                        countryNameGuessing = displayText

                                        chosedCorrect = 1
                                    }
                                }
                                if (chosedCorrect != 1){
                                    looseCount++
                                }
                                if(looseCount >= 3){
                                    loseOrWin.value = !loseOrWin.value
                                    isPressed = !isPressed
                                    refreshCounter++
                                    cuntNum.cancel()
                                    if(!isChecked){
                                        setVeiw = "Lose"
                                    }
//                        mutableList.clear()
                                }

                                var emptyFound = 0
                                for(element in mutableList){
                                    if(element == " _ "){
                                        emptyFound++
                                    }
                                }
                                if(emptyFound == 0){
                                    loseOrWin.value = !loseOrWin.value
                                    isPressed = !isPressed
                                    chooseCorrect = 1
                                    refreshCounter++
                                    cuntNum.cancel()
                                    if(isChecked){
                                        setVeiw = "Winner"
                                    }
//                        mutableList.clear()

                                }

                                if(refreshCounter > 1){
                                    mutableList.clear()
                                    count = 1
                                    looseCount = 0
                                    chooseCorrect = 0
                                    displayText = ""

                                    setContent {
                                        RandomImageProcess()

                                        isChecked = intent.getBooleanExtra("Timer",false)

                                        refreshCounter = 0

                                        FlagguessingandroidapplicationTheme {
                                            // A surface container using the 'background' color from the theme
                                            Surface(
                                                modifier = Modifier.fillMaxSize(),
                                                color = MaterialTheme.colorScheme.background
                                            ) {
                                                GuessHintScreenContent()
                                            }
                                        }
                                    }
                                }


                                text = ""
                            },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(17, 57, 70)
                            )
                        ) {
                            Text(text = if (isPressed) "Next" else "Submit")
                        }
                    }
                }
            }
        }
    }

    fun readJson(context: Context, countryMap: MutableMap<String, String>) {
        // Read JSON from assets
        try {
            val json: String = context.assets.open("Countries.json").bufferedReader().use{it.readText() }

            // Parse JSON using kotlinx.serialization
            val countryDataList = Json.decodeFromString<Map<String, String>>(json)

            // Update the countryMap
            countryMap.putAll(countryDataList)
        } catch (e: Exception) {
            // Handle exceptions (e.g., JSON parsing errors)
            // You may want to log the exception or show an error message
            e.printStackTrace()
        }
    }

    @Composable
    fun RandomImageProcess() {


        val context = LocalContext.current
        val countryMap = remember { mutableMapOf<String, String>() }
        readJson(context, countryMap)

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


        val randomDrawableId = drawableList.random()

        generatedImageDrawableId = randomDrawableId

        // Get the name of the generated drawable resource using reflection
        val drawableName: String = try {
            val fieldName = context.resources.getResourceEntryName(randomDrawableId)
            "R.drawable.$fieldName"
        } catch (e: Resources.NotFoundException) {
            "Unknown"
        }

        generatedImageKey = drawableName.substring(11).uppercase()

        generatedImageName = countryMap[generatedImageKey.uppercase()].toString().uppercase()

        if(count == 1){
            for (index in generatedImageName.indices) {
                val char = generatedImageName[index]
                if (char == ' ') {
                    mutableList.add("   ")
                } else {
                    mutableList.add(" _ ")
                }
            }
            count++
        }

        for (element in mutableList) {
            displayText = displayText + " $element "
        }

        countryNameGuessing = displayText


//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier
//                .fillMaxSize()
//                .border(width = 2.dp, color = Color.Black)
//                .background(Color(17, 57, 70))
//                .padding(10.dp)
//
//        ){
//            Image(
//                painter = painterResource(id = generatedImageDrawableId),
////                painter = painterResource(id = resources.getIdentifier(testImage, "drawable", packageName)),
//                contentDescription = "Country Flag",
//                contentScale = ContentScale.Crop
//            )
//        }



//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .clip(MaterialTheme.shapes.medium),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Image(
//                painter = painterResource(id = randomDrawableId),
//                contentDescription = "Country Flag",
//                contentScale = ContentScale.Crop
//            )
//
//        }

    }

    @Composable
    fun guessHint() {

        var isPressed by remember { mutableStateOf(false) }

        var loseOrWin = remember { mutableStateOf(false) }

        var text by remember { mutableStateOf("") }

        var refreshCounter by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(5f)
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
//                        .background(Color.Blue)
                        .border(5.dp, Color(5, 92, 157))
                        .fillMaxWidth(),
                ) {

                }
                
                Spacer(modifier = Modifier.height(10.dp))

                if(!loseOrWin.value){
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
//                            .background(Color(255, 0, 124))
                            .background(Color(136,225,241),shape = RoundedCornerShape(30))
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                        ) {
                            Text(text = generatedImageName)
                            Text(text = countryNameGuessing, color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "$displayText")
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
//                        .background(Color.Yellow)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth(),
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Enter guessing character here") },
                        )
                    }
                }else{
                    var boxColor = Color(174, 214, 241)
                    var textColour = Color(174, 214, 241)
                    var correctOrWrongMsg = ""

                    if(looseCount==3){
                        textColour = Color(215, 0, 0)
                        correctOrWrongMsg = "Wrong"
                    }
                    if(chooseCorrect == 1){
                        textColour = Color(0, 215, 0)
                        correctOrWrongMsg = "Correct"
                    }


                    Box(
                        modifier = Modifier
                            .weight(3f)
                            .background(Color.Gray)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = correctOrWrongMsg,
                                modifier = Modifier.padding(vertical = 10.dp),
                                fontSize = 20.sp,
                                color = textColour,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = generatedImageName,
                                modifier = Modifier.padding(vertical = 10.dp),
                                fontSize = 20.sp,
                                color = Color(36, 113, 163),
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {
                    var chosedCorrect = 0
                    for (index in generatedImageName.indices) {

                        val char = generatedImageName[index].toString()

                        if (char == "${text.uppercase()}") {

                            countryNameGuessing = "$char $text"

                            mutableList[index] = char

                            displayText = ""

                            for (index in 0 until minOf(generatedImageName.length, mutableList.size)) {

                                displayText = displayText + " ${mutableList[index]} "
                            }

                            countryNameGuessing = displayText

                            chosedCorrect = 1
                        }
                    }
                    if (chosedCorrect != 1){
                        looseCount++
                    }
                    if(looseCount >= 3){
                        loseOrWin.value = !loseOrWin.value
                        isPressed = !isPressed
                        refreshCounter++
//                        mutableList.clear()
                    }

                    var emptyFound = 0
                    for(element in mutableList){
                        if(element == " _ "){
                            emptyFound++
                        }
                    }
                    if(emptyFound == 0){
                        loseOrWin.value = !loseOrWin.value
                        isPressed = !isPressed
                        chooseCorrect = 1
                        refreshCounter++
//                        mutableList.clear()

                    }

                    if(refreshCounter > 1){
                        mutableList.clear()
                        count = 1
                        looseCount = 0
                        chooseCorrect = 0
                        displayText = ""

                        setContent {
                            refreshCounter = 0

                            FlagguessingandroidapplicationTheme {
                                // A surface container using the 'background' color from the theme
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    GuessHintScreenContent()
                                }
                            }
                        }
                    }


                    text = ""
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(17, 57, 70)
                )
            ) {
                Text(text = if (isPressed) "Next" else "Submit")
            }
        }
        }
    }




