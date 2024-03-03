package com.example.flag_guessing_android_application

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.flag_guessing_android_application.ui.theme.FlagguessingandroidapplicationTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class AdvanceLevel : ComponentActivity() {


    var generatedImageName1 = ""
    var generatedImageName2 = ""
    var generatedImageName3 = ""

    var randomDrawableId1 = 0
    var randomDrawableId2 = 0
    var randomDrawableId3 = 0

    var loseCount = 0

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        setContent {
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AdvanceLevelScreenContent() {

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
                    // Add additional actions here
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
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ){
                    RandomImage()
                }
//                Column (
//                    modifier = Modifier
//                        .weight(2f)
//                ){
//                }
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
    fun RandomImage() {


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

        var generatedImageKey1 = drawableName1.substring(11).uppercase()
        generatedImageName1 = countryMap[generatedImageKey1.uppercase()].toString().uppercase()

        var generatedImageKey2 = drawableName2.substring(11).uppercase()
        generatedImageName2 = countryMap[generatedImageKey2.uppercase()].toString().uppercase()

        var generatedImageKey3 = drawableName3.substring(11).uppercase()
        generatedImageName3 = countryMap[generatedImageKey3.uppercase()].toString().uppercase()

//        var textBox1 by remember { mutableStateOf("") }
//        var textBox2 by remember { mutableStateOf("") }
//        var textBox3 by remember { mutableStateOf("") }

        Log.i("","oooo1 ${generatedImageName1}")
        Log.i("","oooo2 ${generatedImageName2}")
        Log.i("","oooo3 ${generatedImageName3}")


        ttt()




    }

    @Composable
    fun ttt(){

        var isTextField1Enabled by remember { mutableStateOf(true) }
        var isTextField2Enabled by remember { mutableStateOf(true) }
        var isTextField3Enabled by remember { mutableStateOf(true) }

        var submitted by remember { mutableStateOf(false) }

        var endGame by remember { mutableStateOf(false) }


        var textBox1 by remember { mutableStateOf("") }
        var textBox2 by remember { mutableStateOf("") }
        var textBox3 by remember { mutableStateOf("") }

        var textFeild1Colour:Color
        var textFeild2Colour:Color
        var textFeild3Colour:Color

        var haveToRefresh by remember { mutableStateOf(false) }

        var refreshCounter by remember { mutableStateOf(0) }

        var result by remember { mutableStateOf("") }

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

            textFeild2Colour = if(!isTextField2Enabled){
                Color(165,214,167)
            }else{
                Color(229,115,115)
            }

            textFeild3Colour = if(!isTextField3Enabled){
                Color(165,214,167)
            }else{
                Color(229,115,115)
            }
        }

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
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(width = 2.dp, color = Color.Black)
                                .background(Color(17, 57, 70))
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = randomDrawableId1),
                                contentDescription = "Country Flag",
                                contentScale = ContentScale.Crop
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
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(width = 2.dp, color = Color.Black)
                                .background(Color(17, 57, 70))
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = randomDrawableId2),
                                contentDescription = "Country Flag",
                                contentScale = ContentScale.Crop
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
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(width = 2.dp, color = Color.Black)
                                .background(Color(17, 57, 70))
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = randomDrawableId3),
                                contentDescription = "Country Flag",
                                contentScale = ContentScale.Crop
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
                                    3.dp,
                                    Color(17, 57, 70),
                                    shape = RoundedCornerShape(20)
                                )
                                .background(Color(185, 206, 172), shape = RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center,
                        ){
                            Text(text = result)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier
                                .weight(7f)
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(10.dp))
                        ) {
                            if(isTextField3Enabled){
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .background(Color.Red, shape = RoundedCornerShape(10.dp))
                                ){
                                    Column(){
                                        Text(text = "First flag")
                                        Text(text = textBox1)
                                        Text(text = generatedImageName1)
                                    }
                                }
                            }
                            if(isTextField2Enabled){
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                                ){
                                    Column(){
                                        Text(text = "Second flag")
                                        Text(text = textBox2)
                                        Text(text = generatedImageName2)
                                    }
                                }
                            }
                            if(isTextField3Enabled){
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .background(Color.Green, shape = RoundedCornerShape(10.dp))
                                ){
                                    Column{
                                        Text(text = "Third flag")
                                        Text(text = textBox3)
                                        Text(text = generatedImageName3)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                            ){
                                Column(){
                                    Text(text = "Score")
                                    Text(text = "score")
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
                        submitted = true


                        if(textBox1.uppercase() == generatedImageName1){
                            Log.i("","First flag answer is correct")
                            isTextField1Enabled = false
                        }else{
                            Log.i("","First flag answer is wrong")
                        }

                        if(textBox2.uppercase() == generatedImageName2){
                            Log.i("","Second flag answer is correct")
                            isTextField2Enabled = false
                        }else{
                            Log.i("","Second flag answer is wrong")
                        }

                        if(textBox3.uppercase() == generatedImageName3){
                            Log.i("","Third flag answer is correct")
                            isTextField3Enabled = false
                        }else{
                            Log.i("","Third flag answer is wrong")
                        }

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


                            }
                        }

                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(17, 57, 70)
                    )
                )
                {
                    Text(text = if (endGame) "Next" else "Submit")
                }

                if(refreshCounter==2){
                    setContent {
                        loseCount = 0
                        refreshCounter = 0
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




