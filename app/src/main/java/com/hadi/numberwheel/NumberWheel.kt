package com.hadi.numberwheel

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun NumberWheel(number: Int, modifier: Modifier = Modifier) {
    val digitsArray = generateDigitsArray(number)
    val animationTracker = remember {
        AnimationTracker()
    }

    LaunchedEffect(number) {
        animationTracker.numberOfTrackedInputChanges++
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(modifier = modifier.animateContentSize(getSizeChangeAnimationSpec())) {
            digitsArray.forEach {character ->
                DigitWheel(digit = character, canAnimate = animationTracker.canAnimate)
            }
        }
    }

}

fun getSizeChangeAnimationSpec(): FiniteAnimationSpec<IntSize> {
    return tween(2000, easing = EaseOutExpo)
}

@Composable
private fun generateDigitsArray(price: Int): CharArray {
    val rawDigitsArray = price.toString().toCharArray().reversedArray()
    return rawDigitsArray
}

@AppPreview
@Composable
private fun NumberWheelPreview() {
    NumberWheel(2300)
}
