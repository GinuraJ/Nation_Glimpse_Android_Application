package com.example.flag_guessing_android_application

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class GuessCountry : ComponentActivity() {

    val reusable = Reusable()

    var generatedImage = "ad"

    var generatedImageName = ""

    var chosedAnswer = "Andorra"

    var isChecked = false

    var generatedImageID = 0

    var orientation = false

    var openBoxMain = false

    var isPressedMain = false

    var refreshCounter = 0
    
    var numCount:Long = 11000

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
                    GuessCountryScreenContent()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("generatedImage",generatedImage)
        outState.putString("generatedImageName",generatedImageName)
        outState.putString("chosedAnswer",chosedAnswer)
        outState.putBoolean("isChecked",isChecked)
        outState.putInt("generatedImageID",generatedImageID)
        outState.putBoolean("orientation", true)
        outState.putBoolean("openBoxMain", openBoxMain)
        outState.putInt("refreshCounter",refreshCounter)
        outState.putBoolean("isPressedMain", isPressedMain)
        outState.putLong("numCount",numCount)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        generatedImage = savedInstanceState.getString("generatedImage","")
        generatedImageName = savedInstanceState.getString("generatedImageName","")
        chosedAnswer = savedInstanceState.getString("chosedAnswer","")
        isChecked = savedInstanceState.getBoolean("isChecked",false)
        generatedImageID = savedInstanceState.getInt("generatedImageID",0)
        orientation = savedInstanceState.getBoolean("orientation",false)
        openBoxMain = savedInstanceState.getBoolean("openBoxMain",false)
        isPressedMain = savedInstanceState.getBoolean("isPressedMain",false)
        refreshCounter = savedInstanceState.getInt("refreshCounter",refreshCounter)
        numCount = savedInstanceState.getLong("numCount",11000)
        
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GuessCountryScreenContent() {

        val openDialog = remember { mutableStateOf(openBoxMain) }

        val context = LocalContext.current

        val countryMap = remember { mutableMapOf<String, String>() }
        reusable.readJson(context, countryMap)

        generatedImageName = countryMap[generatedImage.uppercase()].toString()

        var buttonStates by remember { mutableStateOf(countryMap.keys.associateWith { false }) }

        var isPressed by remember { mutableStateOf(isPressedMain) }

        Log.i("","www $isChecked")

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

                if(isPressed){
                    isChecked = false
                }else{
                    isPressed = !isPressed
                    if(generatedImage == chosedAnswer){
//                        if(isPressed){
//                            Toast.makeText(context,"🥳 Congrats",Toast.LENGTH_SHORT).show()
//                        }
                        openDialog.value = !openDialog.value
                    }else if(generatedImage != chosedAnswer) {
//                        if(isPressed){
//                            Toast.makeText(context,"😪 Oops",Toast.LENGTH_SHORT).show()
//                        }
                        openDialog.value = !openDialog.value
                    }

                    refreshCounter++
                }


            }
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // TopAppBar for navigation

            TopAppBar(
                title = {
                    Text(text = "Guess Country")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val NavigateGuessHint = Intent(this@GuessCountry,MainActivity::class.java)
                            NavigateGuessHint.putExtra("Timer",isChecked)
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
//                    .background(Color(236, 226, 208))
                    .background(Color.White)

                    .padding(vertical = 8.dp)
            ) {
                Column (
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ){
                    RandomImage()
                }
                Column (
                    modifier = Modifier
                        .weight(2f)
                ){

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    ) {
                        if(!openDialog.value){
                            LazyColumn(
                                modifier = Modifier
                                    .weight(5f)
                                    .padding(vertical = 8.dp)
                            ) {
                                items(countryMap.size) { index ->
                                    val (key, value) = countryMap.entries.toList()[index]


                                    Button(

                                        onClick = {
                                            chosedAnswer = key

                                            buttonStates = buttonStates.mapValues { (btnKey, _) ->
                                                btnKey == key
                                            }
                                            Log.i("","kkk $chosedAnswer $generatedImage")


                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(30),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (buttonStates[key] == true) Color(162, 103, 105) else Color(213, 185, 178),
                                        )

                                    ) {
                                        Text(text = value, color = Color.Black, fontSize = 20.sp)
                                    }
                                }
                            }
                        }else{
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                            ){
                                val cornerSize = 10.dp

                                var textColour: Color
                                var boxColor = Color(174, 214, 241)
                                var correctOrWrong:String

                                if(generatedImage == chosedAnswer){
                                    textColour = Color(0, 255, 0)
                                    correctOrWrong = "Correct"
                                }else {
                                    textColour = Color(255, 0, 0)
                                    correctOrWrong = "Wrong"
                                }

                                if(openDialog.value){
                                    Popup(
                                        alignment = Alignment.TopStart,
                                        properties = PopupProperties()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxHeight(0.5f)
                                                .fillMaxWidth()
//                                    .height(400.dp)
                                                .padding(10.dp)
                                                .background(
                                                    Color(236, 226, 208),
                                                    RoundedCornerShape(cornerSize)
                                                )
                                                .border(
                                                    1.dp,
                                                    Color.Black,
                                                    RoundedCornerShape(cornerSize)
                                                ),
                                        ){
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Text(
                                                    text = "${correctOrWrong}",
                                                    modifier = Modifier.padding(vertical = 10.dp),
                                                    fontSize = 36.sp,
                                                    color = textColour,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = "${generatedImageName}",
                                                    modifier = Modifier.padding(vertical = 10.dp),
                                                    fontSize = 36.sp,
                                                    color = Color(36, 113, 163),
                                                )

                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Button(
                            onClick = {
                                isPressed = !isPressed
                                if(generatedImage == chosedAnswer){
                                    if(isPressed){
                                        cuntNum.cancel()
                                        setVeiw = "Winner"
                                    }
                                    openDialog.value = !openDialog.value
                                    openBoxMain = !openBoxMain
                                }else if(generatedImage != chosedAnswer) {
                                    if(isPressed){
                                        cuntNum.cancel()
                                        setVeiw = "Lose"
                                    }
                                    openDialog.value = !openDialog.value
                                    openBoxMain = !openBoxMain
                                }
                                refreshCounter++
                            },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(88, 44, 77)
                            )
                        )
                        {
                            Text(text = if (isPressed) "Next" else "Submit")
                        }

                        if(refreshCounter > 1){

                            orientation = false

                            openBoxMain = false

                            isPressedMain = false

                            numCount = 11000

                            setContent {

                                randomProcess()

                                isChecked = intent.getBooleanExtra("Timer",false)

                                refreshCounter = 0

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
                }
            }
        }
    }

//
//    @Composable
//    fun loadCountryList() {
//
//        val openDialog = remember { mutableStateOf(false) }
//
//        var refreshCounter by remember { mutableStateOf(0) }
//
//        val context = LocalContext.current
//
//        val countryMap = remember { mutableMapOf<String, String>() }
//        readJson(context, countryMap)
//
//        generatedImageName = countryMap[generatedImage.uppercase()].toString()
//
//        var buttonStates by remember { mutableStateOf(countryMap.keys.associateWith { false }) }
//
//        var isPressed by remember { mutableStateOf(false) }
//
//        Column(
//            modifier = Modifier
//                .padding(horizontal = 8.dp)
//        ) {
//            if(!openDialog.value){
//                LazyColumn(
//                    modifier = Modifier
//                        .weight(5f)
//                        .padding(vertical = 8.dp)
////                    .height(300.dp)
//                ) {
//                    items(countryMap.size) { index ->
//                        val (key, value) = countryMap.entries.toList()[index]
//
//
//                        Button(
//
//                            onClick = {
//                                chosedAnswer = key
//
//                                buttonStates = buttonStates.mapValues { (btnKey, _) ->
//                                    btnKey == key
//                                }
//                                Log.i("","kkk $chosedAnswer $generatedImage")
//
//
//                            },
//                            modifier = Modifier
//                                .fillMaxWidth(),
////                            .padding(8.dp),
//                            shape = RoundedCornerShape(30),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (buttonStates[key] == true) Color(26, 93, 26) else Color(17,190,121),
//                            )
////                            colors = ButtonDefaults.buttonColors(
////                                containerColor = Color(26, 93, 26)
////                            )
//                        ) {
//                            Text(text = value, color = Color.Black, fontSize = 20.sp)
//                        }
//                    }
//                }
//            }else{
//                Box(
//                    modifier = Modifier
//                        .weight(5f)
//                ){
//                    val cornerSize = 10.dp
//
//                    var textColour: Color
//                    var boxColor = Color(174, 214, 241)
//                    var correctOrWrong:String
//
//                    if(generatedImage == chosedAnswer){
//                        textColour = Color(0, 255, 0)
//                        correctOrWrong = "Correct"
//                    }else {
//                        textColour = Color(255, 0, 0)
//                        correctOrWrong = "Wrong"
//                    }
//
//                    if(openDialog.value){
//                        Popup(
//                            alignment = Alignment.TopStart,
//                            properties = PopupProperties()
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxHeight(0.5f)
//                                    .fillMaxWidth()
////                                    .height(400.dp)
//                                    .padding(10.dp)
//                                    .background(boxColor, RoundedCornerShape(cornerSize))
//                                    .border(1.dp, Color.Black, RoundedCornerShape(cornerSize)),
//                            ){
//                                Column(
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.Center,
//                                    modifier = Modifier.fillMaxSize()
//                                ) {
//                                    Text(
//                                        text = "${correctOrWrong}",
//                                        modifier = Modifier.padding(vertical = 10.dp),
//                                        fontSize = 36.sp,
//                                        color = textColour,
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                    Text(
//                                        text = "${generatedImageName}",
//                                        modifier = Modifier.padding(vertical = 10.dp),
//                                        fontSize = 36.sp,
//                                        color = Color(36, 113, 163),
//                                    )
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//
//            Button(
//                onClick = {
//                    isPressed = !isPressed
//                    if(generatedImage == chosedAnswer){
//                        if(isPressed){
//                            Toast.makeText(context,"🥳 Congrats",Toast.LENGTH_SHORT).show()
//                        }
//                        openDialog.value = !openDialog.value
//                    }else if(generatedImage != chosedAnswer) {
//                        if(isPressed){
//                            Toast.makeText(context,"😪 Oops",Toast.LENGTH_SHORT).show()
//                        }
//                        openDialog.value = !openDialog.value
//                    }
//
//                    refreshCounter++
//                          },
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth(),
//                shape = RoundedCornerShape(20),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(17, 57, 70)
//                )
//            )
//            {
//                Text(text = if (isPressed) "Next" else "Submit")
//            }
//
//            if(refreshCounter > 1){
//                setContent {
//                    refreshCounter = 0
//                    FlagguessingandroidapplicationTheme {
//                        // A surface container using the 'background' color from the theme
//                        Surface(
//                            modifier = Modifier.fillMaxSize(),
//                            color = MaterialTheme.colorScheme.background
//                        ) {
//                            GuessCountryScreenContent()
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//

//
//    fun readJson(context: Context, countryMap: MutableMap<String, String>) {
//        // Read JSON from assets
//        try {
//            val json: String = context.assets.open("Countries.json").bufferedReader().use{it.readText() }
//
//            // Parse JSON using kotlinx.serialization
//            val countryDataList = Json.decodeFromString<Map<String, String>>(json)
//
//            // Update the countryMap
//            countryMap.putAll(countryDataList)
//        } catch (e: Exception) {
//            // Handle exceptions (e.g., JSON parsing errors)
//            // You may want to log the exception or show an error message
//            e.printStackTrace()
//        }
//    }

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


        val randomDrawableId = drawableList.random()

        generatedImageID = randomDrawableId

        // Get the name of the generated drawable resource using reflection
        val drawableName: String = try {
            val fieldName = context.resources.getResourceEntryName(generatedImageID)
            "R.drawable.$fieldName"
        } catch (e: Resources.NotFoundException) {
            "Unknown"
        }

        generatedImage = drawableName.substring(11).uppercase()

    }

    @Composable
    fun RandomImage() {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .border(width = 2.dp, color = Color.Black)
                .background(Color(191, 181, 192))
                .padding(10.dp)

        ) {
            Image(
                painter = painterResource(id = generatedImageID),
//                painter = painterResource(id = resources.getIdentifier(testImage, "drawable", packageName)),
                contentDescription = "Country Flag",
                contentScale = ContentScale.Crop
            )
        }


    }

}




