package com.example.multiplatform.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplatform.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().title())
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingView(text: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        backgroundColor = Color(0xFFEEEEEE),
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                title = {
                    Image(
                        painterResource(id = R.drawable.toss_logo_primary),
                        contentDescription = "Toss Icon"
                    )
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Localized description"
                        )
                    }
                },
                backgroundColor = Color(0xFFEEEEEE)
            )
        },
        content = {
            HomeFragment()
        },
        bottomBar = {
            //var selectedItem by remember { mutableIntStateOf(0) }
            val items = listOf("홈", "혜택", "토스페이", "주식", "전체")
            val itemsIcon = listOf(R.drawable.tab1, R.drawable.tab2, R.drawable.tab3, R.drawable.tab4, R.drawable.tab5)

            NavigationBar(
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painterResource(id = itemsIcon[index]),
                                contentDescription = items[index],
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = { Text(item) },
                        selected = false,
                        onClick = {  }
                    )
                }
            }
        }
    )
}

@Composable
fun HomeFragment() {
    LazyColumn(
        Modifier
            .padding(16.dp, 0.dp)
            .fillMaxSize()
    ) {
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { TossBank("토스뱅크") }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { Asset() }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { Invest() }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { Consumption() }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { SquareCardLayout() }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { SettingView() }
        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TossBank(text: String) {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        border = null
    ) {
        CardContentLayout(text = text)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Asset() {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        border = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CardContentLayout(text = "자산")

            val textList = listOf("입출금통장", "신한카드", "토스뱅크 통장", "국민카드", "카카오뱅크 카드")
            val cashList = listOf("127,085,000원", "2500,000원", "150,000원", "10,000원", "88,000원")
            val iconList = listOf(R.drawable.kakao, R.drawable.sinhan, R.drawable.toss, R.drawable.kb, R.drawable.kakao)
            Column(
                modifier = Modifier.padding(16.dp, 8.dp),
                //contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (i in 0 .. 4) {
                    CashList(name = textList[i], cash = cashList[i], iconList[i])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Invest() {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        border = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CardContentLayout(text = "투자")

            val textList = listOf("주식", "주식", "펀드")
            val cashList = listOf("1,000,000원", "500,000원", "100,000원")
            val iconList = listOf(R.drawable.sinhan, R.drawable.kb, R.drawable.kakao)
            Column(
                modifier = Modifier.padding(16.dp, 8.dp),
                //contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (i in 0 .. 2) {
                    InvestCashList(name = textList[i], cash = cashList[i], iconList[i])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Consumption() {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        border = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CardContentLayout(text = "소비")
        }
    }
}

@Composable
fun SquareCardLayout() {
    LazyRow {
        item { SquareCard(text1 = "1분만에", text2 = "내 보험", text3 = "전부 조회", image = R.drawable.image1) }
        item { Spacer(modifier = Modifier.width(10.dp)) }
        item { SquareCard(text1 = "최근", text2 = "대출 한도", text3 = "조회", image = R.drawable.image2) }
        item { Spacer(modifier = Modifier.width(10.dp)) }
        item { SquareCard(text1 = "자주", text2 = "신용점수", text3 = "보기", image = R.drawable.image3) }
        item { Spacer(modifier = Modifier.width(10.dp)) }
        item { SquareCard(text1 = "인기", text2 = "더보기", text3 = "", image = null) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SquareCard(text1: String, text2: String, text3: String, image: Int?) {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        border = null
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = text1,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = text2,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Text(
                text = text3,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1F))
            if (image != null) {
                Image(
                    painterResource(id = image),
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun CardContentLayout(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.TopStart),
            fontWeight = FontWeight.ExtraBold
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Localized description",
            modifier = Modifier.
            align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun CashList(name: String, cash: String, image: Int) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = image),
            contentDescription = "Localized description",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(name)
            Text(cash, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.weight(1F))
        Button(colors = ButtonDefaults.buttonColors(contentColor = Color.Black, backgroundColor = Color.LightGray),
            onClick = { /*...*/ }
        ) {
            Text(text = "송금")
        }
    }
}

@Composable
fun InvestCashList(name: String, cash: String, image: Int) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = image),
            contentDescription = "Localized description",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(name)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(cash, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "-80%", color = Color.Blue, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SettingView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ImageButton(image = Icons.Filled.Settings, text = "화면 설정")
        Spacer(modifier = Modifier.width(15.dp))
        ImageButton(image = Icons.Filled.Add, text = "자산 추가")
    }
}

@Composable
fun ImageButton(image: ImageVector, text: String) {
    Card(
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = image,
                contentDescription = "Localized description"
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = text, fontSize = 13.sp)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView(Greeting().title())
    }
}

@Preview
@Composable
fun ItemPreview() {
    MyApplicationTheme {
        SettingView()
    }
}