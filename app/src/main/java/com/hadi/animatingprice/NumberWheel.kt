package com.hadi.animatingprice

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NumberWheel(number: Int, modifier: Modifier = Modifier) {
    val digitsArray = generateDigitsArray(number)
    val animationTracker = remember {
        AnimationTracker()
    }

    LaunchedEffect(number) {
        animationTracker.numberOfTrackedInputChanges++
    }

    Row(
        modifier = modifier
    ) {
        digitsArray.forEachIndexed { index, character ->
            key(index) {
                if (character != null) {
                    DigitWheel(
                        digit = character,
                        canAnimate = animationTracker.canAnimate
                    )
                }
            }
        }
    }
}

@Composable
private fun generateDigitsArray(price: Int): Array<Char?> {
    val rawDigitsArray = price.toString().toCharArray()
    val minSupportedDigitsCount = 10
    val digitsCount = Integer.max(minSupportedDigitsCount, rawDigitsArray.size)
    val digitsArray = Array(digitsCount) { index ->
        val sizeDiff = digitsCount - rawDigitsArray.size
        if (index >= sizeDiff) {
            rawDigitsArray[index - sizeDiff]
        } else {
            null
        }
    }
    return digitsArray
}

@Preview
@Composable
private fun NumberWheelPreview() {
    NumberWheel(2300)
}
