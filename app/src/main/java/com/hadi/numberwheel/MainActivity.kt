package com.hadi.numberwheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hadi.numberwheel.ui.theme.NumberWheelTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberWheelTheme {
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
        }
    }
}