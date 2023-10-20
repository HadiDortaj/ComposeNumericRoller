package com.hadi.numberwheel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun MainScreen() {
    var price by remember {
        mutableStateOf(2)
    }
    LaunchedEffect(key1 = Unit) {
        val changes = listOf(12)
        changes.forEach {
            delay(3000)
            price = it
        }
    }
    NumberWheel(price)
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}