package com.example.multiplatform.android

import android.os.Bundle
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingView(text: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        backgroundColor = Color(0xFFEEEEEE),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = R.drawable.toss_symbol_primary),
                            contentDescription = "Toss Icon",
                            modifier = Modifier.size(35.dp)
                        )
                        Text(
                            text,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                /*navigationIcon = {
                    IconButton(onClick = { *//* doSomething() *//* }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },*/
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
                backgroundColor = Color(0xFFFFFFFF)
            )
        },
        content = { innerPadding ->
            LazyColumn(
                Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxSize()
            ) {
                item { TossBank("토스뱅크") }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item { Asset(innerPadding) }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item { Invest(innerPadding) }
            }
        },
        bottomBar = {
            //var selectedItem by remember { mutableIntStateOf(0) }
            val items = listOf("홈", "혜택", "토스페이", "주식", "전체")
            val itemsIcon = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.PlayArrow, Icons.Filled.AccountBox, Icons.Filled.Menu)

            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(itemsIcon[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = false,
                        onClick = {  }
                    )
                }
            }
        }
    )
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
fun Asset(innerPadding: PaddingValues) {
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
            BankList(innerPadding = innerPadding)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Invest(innerPadding: PaddingValues) {
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
            BankList(innerPadding = innerPadding)
        }
    }
    /*LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val list = (0..5).map { it.toString() }
        items(count = list.size) {
            Card(
                onClick = { *//* Do something *//* },
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text("Clickable", Modifier.align(Alignment.Center))
                }
            }
            *//*Text(
                text = list[it],
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )*//*
        }
    }*/
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
            modifier = Modifier.
            align(Alignment.TopStart),
            fontWeight = FontWeight.Bold
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
fun BankList(innerPadding: PaddingValues) {
    val textList = listOf("입출금통장", "신한카드", "토스뱅크 통장", "국민카드", "하나카드", "카카오뱅크 카드")
    val cashList = listOf("1,000,000원", "500,000원", "100,000원", "10,000원", "12,345원", "88,000원")
    Column(
        modifier = Modifier.padding(16.dp, 8.dp),
        //contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 0 .. 5) {
            CashList(name = textList[i], cash = cashList[i])
        }
    }
}

@Composable
fun CashList(name: String, cash: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Localized description",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(name)
            Text(cash)
        }
        Spacer(modifier = Modifier.weight(1F))
        Button(
            onClick = { /*...*/ }
        ) {
            Text(text = "송금")
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
