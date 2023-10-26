package com.hadi.numberwheel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun DemoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF1EBE3))
    ) {
        var number by remember {
            mutableStateOf(123456)
        }
        LaunchedEffect(key1 = Unit) {
            val changes = listOf(654321, 123456, 123458, 123398, 423398, 411398, 123456, 23456)
            delay(1000)
            while (true) {
                changes.forEach {
                    number = it
                    delay(2000)
                }
            }
        }
        NumberWheel(number = number, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    DemoScreen()
}