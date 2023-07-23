package com.example.multiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Toss Icon"
                    )
                    Text(
                        text,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Content(innerPadding)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(innerPadding: PaddingValues) {
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val list = (0..20).map { it.toString() }
        items(count = list.size) {
            Card(
                onClick = { /* Do something */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text("Clickable", Modifier.align(Alignment.Center))
                }
            }
            /*Text(
                text = list[it],
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )*/
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
