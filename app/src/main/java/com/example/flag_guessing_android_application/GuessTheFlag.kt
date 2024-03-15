package com.example.flag_guessing_android_application

import android.content.Context
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
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
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


class GuessTheFlag : ComponentActivity() {

    var isChecked = false
    var generatedDrawableID1 = 0
    var generatedDrawableID2 = 0
    var generatedDrawableID3 = 0
    var randomImageName = ""
    var generatedImageName1 = ""
    var generatedImageName2 = ""
    var generatedImageName3 = ""
    var userSelectedFlagName = ""
    var orientation = false
    var ChosedTheAnswerMain = false
    var userAnswerIs = ""
    var numCount:Long = 11000

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            if(!orientation){
                RandomImage()
            }

            isChecked = intent.getBooleanExtra("Timer",false)

            FlagguessingandroidapplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessTheFlagScreenContent()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("orientation", true)
        outState.putInt("generatedDrawableID1",generatedDrawableID1)
        outState.putInt("generatedDrawableID2",generatedDrawableID2)
        outState.putInt("generatedDrawableID3",generatedDrawableID3)
        outState.putString("randomImageName",randomImageName)
        outState.putString("generatedImageName1",generatedImageName1)
        outState.putString("generatedImageName2",generatedImageName2)
        outState.putString("generatedImageName3",generatedImageName3)
        outState.putString("userSelectedFlagName",userSelectedFlagName)
        outState.putBoolean("ChosedTheAnswerMain", ChosedTheAnswerMain)
        outState.putString("userAnswerIs",userAnswerIs)
        outState.putLong("numCount",numCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        orientation = savedInstanceState.getBoolean("orientation",false)
        generatedDrawableID1 = savedInstanceState.getInt("generatedDrawableID1",0)
        generatedDrawableID2 = savedInstanceState.getInt("generatedDrawableID2",0)
        generatedDrawableID3 = savedInstanceState.getInt("generatedDrawableID3",0)
        randomImageName = savedInstanceState.getString("randomImageName","")
        generatedImageName1 = savedInstanceState.getString("generatedImageName1","")
        generatedImageName2 = savedInstanceState.getString("generatedImageName2","")
        generatedImageName3 = savedInstanceState.getString("generatedImageName3","")
        ChosedTheAnswerMain = savedInstanceState.getBoolean("ChosedTheAnswerMain",false)
        userAnswerIs = savedInstanceState.getString("userAnswerIs","")
        numCount = savedInstanceState.getLong("numCount",11000)


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GuessTheFlagScreenContent() {

        fun CheckTheAnswer(){
            if(userSelectedFlagName == randomImageName){
                Log.i("","oooo Win Win Win")
                userAnswerIs = "Correct"
            }else{
                Log.i("","oooo Ooooopppss")
                userAnswerIs = "Wrong"
            }
        }

        var ChosedTheAnswer = remember { mutableStateOf(ChosedTheAnswerMain) }

        var refreshCounter by remember { mutableStateOf(0) }

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
                ChosedTheAnswer.value = true
                userAnswerIs = "Time is up"
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // TopAppBar for navigation
            TopAppBar(
                title = {
                    Text(text = "Guess The Flag")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val NavigateGuessHint = Intent(this@GuessTheFlag,MainActivity::class.java)
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
                    .background(Color(236, 226, 208))
                    .padding(vertical = 8.dp)
            ) {
                Column (
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ){

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(MaterialTheme.shapes.medium),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(88, 44, 77),shape = RoundedCornerShape(10.dp))
                                    .border(2.dp, Color(24,21,9),shape = RoundedCornerShape(10.dp))
                                    .weight(0.5f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = randomImageName,
                                    color = Color(236, 226, 208),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp)
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            if(!ChosedTheAnswer.value){
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                        .border(width = 2.dp, color = Color.Black)
                                        .background(Color(162, 103, 105))
                                        .padding(10.dp)
                                        .clickable {
                                            ChosedTheAnswer.value = !ChosedTheAnswer.value
                                            ChosedTheAnswerMain = !ChosedTheAnswerMain
                                            CheckTheAnswer()
                                            cuntNum.cancel()
                                            setVeiw = "Finished"
                                        }
                                ) {
                                    Image(
                                        painter = painterResource(id = generatedDrawableID1),
                                        contentDescription = "Country Flag",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                        .border(width = 2.dp, color = Color.Black)
                                        .background(Color(162, 103, 105))
                                        .padding(10.dp)
                                        .clickable {
                                            ChosedTheAnswer.value = !ChosedTheAnswer.value
                                            userSelectedFlagName = generatedImageName2
                                            ChosedTheAnswerMain = !ChosedTheAnswerMain
                                            CheckTheAnswer()
                                            cuntNum.cancel()
                                            setVeiw = "Finished"
                                        }
                                ) {
                                    Image(
                                        painter = painterResource(id = generatedDrawableID2),
                                        contentDescription = "Country Flag",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                        .border(width = 2.dp, color = Color.Black)
                                        .background(Color(162, 103, 105))
                                        .padding(10.dp)
                                        .clickable {
                                            ChosedTheAnswer.value = !ChosedTheAnswer.value
                                            userSelectedFlagName = generatedImageName3
                                            ChosedTheAnswerMain = !ChosedTheAnswerMain
                                            CheckTheAnswer()
                                            cuntNum.cancel()
                                            setVeiw = "Finished"
                                        }
                                ) {
                                    Image(
                                        painter = painterResource(id = generatedDrawableID3),
                                        contentDescription = "Country Flag",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }else{
                                Column(
                                    modifier = Modifier
                                        .weight(3f)
                                        .background(Color.Yellow)
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(5f)
                                    ) {
                                        Text(text = userAnswerIs)
                                    }
                                    Button(
                                        onClick = {
                                            ChosedTheAnswer.value = !ChosedTheAnswer.value
                                            refreshCounter++
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(20),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(17, 57, 70)
                                        )
                                    )
                                    {
                                        Text(text = "Next")
                                    }
                                }
                            }

                            if(refreshCounter != 0){

                                orientation = false

                                ChosedTheAnswerMain = false

                                userAnswerIs = ""

                                numCount = 11000

                                setContent {

                                    RandomImage()

                                    refreshCounter = 0

                                    isChecked = intent.getBooleanExtra("Timer",false)

                                    FlagguessingandroidapplicationTheme {
                                        // A surface container using the 'background' color from the theme
                                        Surface(
                                            modifier = Modifier.fillMaxSize(),
                                            color = MaterialTheme.colorScheme.background
                                        ) {
                                            GuessTheFlagScreenContent()
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


        val randomDrawableId1 = drawableList.random()
        generatedDrawableID1 = randomDrawableId1

        val randomDrawableId2 = drawableList.random()
        generatedDrawableID2 = randomDrawableId2

        val randomDrawableId3 = drawableList.random()
        generatedDrawableID3 = randomDrawableId3


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

        var imageNameList = listOf(generatedImageName1,generatedImageName2,generatedImageName3)
        randomImageName = imageNameList.random()

    }
}




