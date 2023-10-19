package com.hadi.animatingprice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hadi.animatingprice.ui.theme.AnimatingPriceTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatingPriceTheme {
                // A surface container using the 'background' color from the theme
                var price by remember {
                    mutableStateOf(23)
                }

                LaunchedEffect(key1 = Unit) {
                    delay(3000)

                    price = 93

                    delay(3000)
                    price = 423

                }
                Greeting(price)
            }
        }
    }
}

/*
steps:
    - implement the behavior with fixed height and no animation customization and with only one letter
    - add multiple items
    - Remove fixed height
    - customize animation
    - (Maybe add support for comma)
    - design better(texts, screen)
    - share on Github
 */

@Composable
fun Greeting(price: Int) {

    val priceCharacters = generateCharactersArray(price)

    Row(
        modifier = Modifier
            .height(50.dp)
    ) {
        priceCharacters.forEachIndexed { index, character ->
            key(index, character) {
                if (character != null) {
                    Log.i("HADI", "index: $index $character")
                    CharacterColumn(character)
                }
            }
        }

    }
}

@Composable
private fun CharacterColumn(character: Char) {
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = 0)
    LaunchedEffect(key1 = character) {
        lazyListState.animateScrollToItem(getCharacterIndex(character))
    }
    LazyColumn(state = lazyListState) {
        items(items = getItems()) { item ->
            Text(text = item, fontSize = 40.sp, modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun generateCharactersArray(price: Int): Array<Char?> {
    val charactersArray = price.toString().toCharArray()
    val supportedCharacters = 10
    val modifiedArray = Array(supportedCharacters) { index ->
        val sizeDiff = supportedCharacters - charactersArray.size
        if (index >= sizeDiff) {
            charactersArray[index - sizeDiff]
        } else {
            null
        }
    }
    return modifiedArray
}

fun getItems(): List<String> {
    return listOf(*(0..9).map { it.toString() }.toTypedArray())
}

fun getCharacterIndex(character: Char): Int {
    return getItems().indexOfFirst { it == character.toString() }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AnimatingPriceTheme {
//        Greeting(4)
//    }
//}