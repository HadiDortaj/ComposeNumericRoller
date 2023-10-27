package com.hadi.numericroller

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
import com.hadi.numericroller.ui.theme.Alabaster
import kotlinx.coroutines.delay

@Composable
fun DemoScreen() {
    Box(modifier = Modifier
            .fillMaxSize()
            .background(Alabaster)
    ) {
        var number by remember {
            mutableStateOf(123456)
        }
        LaunchedEffect(key1 = Unit) {
            val changes = listOf(654321, 123456, 123458, 123398, 423398, 469398, 123456)
            delay(1000)
            while (true) {
                changes.forEach {
                    number = it
                    delay(2000)
                }
            }
        }
        NumericRoller(number = number, modifier = Modifier.align(Alignment.Center))
    }
}

@AppPreview
@Composable
private fun DemoScreenPreview() {
    DemoScreen()
}