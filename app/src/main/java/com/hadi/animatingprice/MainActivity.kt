package com.hadi.animatingprice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hadi.animatingprice.ui.theme.AnimatingPriceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatingPriceTheme {
                // A surface container using the 'background' color from the theme
                Greeting()
            }
        }
    }
}

/*
steps:
    1- implement the behavior with fixed height and no animation customization and with only one letter
    2- add multiple items
    3- Remove fixed height
    4- customize animation
    5- design better(texts, screen)
    6- share on Github
 */

@Composable
fun Greeting() {
    val items = listOf(",", *(0..10).map { it.toString() }.toTypedArray())

    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = 0)
    LaunchedEffect(key1 = Unit) {
        lazyListState.animateScrollToItem(5)
    }
    Box(
        modifier = Modifier
            .clipToBounds()
            .height(50.dp)
    ) {
        LazyColumn(state = lazyListState) {
            items(items = items) { item ->
                Text(text = item, fontSize = 40.sp, modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimatingPriceTheme {
        Greeting()
    }
}