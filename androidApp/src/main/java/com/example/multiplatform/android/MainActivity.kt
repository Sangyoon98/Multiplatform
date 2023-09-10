package com.example.multiplatform.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multiplatform.Greeting
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingView() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem.HomeFragment,
        BottomNavItem.BenefitFragment,
        BottomNavItem.TossPayFragment,
        BottomNavItem.StockFragment,
        BottomNavItem.AllFragment
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        backgroundColor = Color(0xFFEEEEEE),
        bottomBar = {
            NavigationBar(
                contentColor = Color.Gray,
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painterResource(id = item.icon),
                                contentDescription = stringResource(id = item.title),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = { Text(stringResource(id = item.title)) },
                        selected = currentRoute == item.screenRoute,
                        alwaysShowLabel = true,
                        onClick = {
                            navController.navigate(item.screenRoute) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        Box(Modifier.padding(it)){
            NavigationGraph(navController = navController)
        }
    }
}


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.HomeFragment.screenRoute) {
        composable(BottomNavItem.HomeFragment.screenRoute) {
            HomeFragment()
        }
        composable(BottomNavItem.BenefitFragment.screenRoute) {
            BenefitFragment()
        }
        composable(BottomNavItem.TossPayFragment.screenRoute) {
            TossPayFragment()
        }
        composable(BottomNavItem.StockFragment.screenRoute) {
            StockFragment()
        }
        composable(BottomNavItem.AllFragment.screenRoute) {
            AllFragment()
        }
    }
}

/**홈**/
@Composable
fun HomeFragment() {
    Column {
        TopAppBar(
            elevation = 0.dp,
            title = {
                Image(
                    painterResource(id = R.drawable.toss_logo_primary),
                    contentDescription = "Toss Icon",
                    modifier = Modifier.padding(0.dp)
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
        LazyColumn {
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
}

/** 혜택 **/
@Composable
fun BenefitFragment() {
    LazyColumn(
        Modifier
            .background(Color.White)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.benefit_fragment),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(16.dp, 20.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.padding(20.dp))
        }
        item {
            val benefitList = listOf(benefitList.A, benefitList.B, benefitList.C, benefitList.D, benefitList.E, benefitList.F, benefitList.G, benefitList.A, benefitList.B, benefitList.C, benefitList.D, benefitList.E, benefitList.F, benefitList.G)
            benefitList.forEach {
                BenefitContentLayout(image = it.image, title = it.title, text = it.text)
            }
        }
    }

}

enum class benefitList(val image: Int, val title: String, val text: String) {
    A(R.drawable.bene_1, "친구와 함께 토스 켜고", "포인트 받기"),
    B(R.drawable.bene_2, "이번 주 미션", "얼마 받을지 보기"),
    C(R.drawable.bene_4, "라이브 쇼핑", "포인트 받기"),
    D(R.drawable.bene_5, "행운퀴즈", "추가 혜택 보기"),
    E(R.drawable.bene_6, "머니 알림 받고", "20원 받기"),
    F(R.drawable.bene_7, "만보기", "140원 받기"),
    G(R.drawable.bene_8, "새로운 카드 쓰고", "결제지원금 받기")
}

/** 토스 페이 **/
@Composable
fun TossPayFragment() {
    TossBank(text = "TossPayFragment")
}

/** 주식 **/
@Composable
fun StockFragment() {
    TossBank(text = "StockFragment")
}

/** 전체 **/
@Composable
fun AllFragment() {
    TossBank(text = "AllFragment")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TossBank(text: String) {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
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
    LazyRow(
        contentPadding = PaddingValues(16.dp, 0.dp)
    ) {
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
        modifier = Modifier
            .fillMaxSize()
            .clickable { },
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
        modifier = Modifier
            .clickable { }
            .fillMaxSize(),
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageButton(image: ImageVector, text: String) {
    Card(
        onClick = {  /*...*/ },
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

@Composable
fun BenefitContentLayout(image: Int, title: String, text: String) {
    Row(
        modifier = Modifier
            .clickable { }
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = image),
            contentDescription = "Localized description",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold)
            Text(text, color = Color.Blue)
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView()
    }
}

@Preview
@Composable
fun HomePreview() {
    MyApplicationTheme {
        HomeFragment()
    }
}

@Preview
@Composable
fun BenefitPreview() {
    MyApplicationTheme {
        BenefitFragment()
    }
}

@Preview
@Composable
fun TossPayPreview() {
    MyApplicationTheme {
        TossPayFragment()
    }
}

@Preview
@Composable
fun StockPreview() {
    MyApplicationTheme {
        StockFragment()
    }
}

@Preview
@Composable
fun AllPreview() {
    MyApplicationTheme {
        AllFragment()
    }
}