package com.hadi.numberwheel

import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val EMPTY_ITEM_INDEX = 0
private const val ANIMATION_DURATION_IN_MILLIS = 2000
private val DIGIT_HEIGHT = 80.dp

@Composable
fun DigitWheel(digit: Char, canAnimate: Boolean, modifier: Modifier = Modifier) {
    val initialFirstVisibleItemIndex = if (canAnimate) EMPTY_ITEM_INDEX else getDigitIndex(digit)
    val lazyListState =
        rememberLazyListState(initialFirstVisibleItemIndex = initialFirstVisibleItemIndex)

    val digitHeightInPixels = with(LocalDensity.current) {
        DIGIT_HEIGHT.toPx()
    }

    LaunchedEffect(key1 = digit) {
        val scrollValue = calculateScrollValue(
            digit = digit,
            firstVisibleItemIndex = lazyListState.firstVisibleItemIndex,
            digitHeightInPixels = digitHeightInPixels
        )
        lazyListState.animateScrollBy(
            value = scrollValue,
            animationSpec = getAnimationSpec()
        )
    }

    LazyColumn(
        modifier = modifier.height(DIGIT_HEIGHT),
        state = lazyListState,
        userScrollEnabled = false
    ) {
        items(items = getWheelItems()) { item ->
            Text(
                text = item, fontSize = 60.sp,
                modifier = Modifier.height(DIGIT_HEIGHT).width(50.dp),
                color = Color(0xffBC9E4C),
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            R.font.libre_baskerville_regular,
                            FontWeight.Normal
                        )
                    )
                )
            )
        }
    }
}

private fun calculateScrollValue(
    digit: Char,
    firstVisibleItemIndex: Int,
    digitHeightInPixels: Float
): Float {
    val digitIndex = getDigitIndex(digit = digit)
    val diff = digitIndex - firstVisibleItemIndex
    val scrollValue = diff * digitHeightInPixels
    return scrollValue
}

private fun getWheelItems(): List<String> {
    return listOf("", *(0..9).map { it.toString() }.toTypedArray())
}

private fun getDigitIndex(digit: Char): Int {
    return getWheelItems().indexOfFirst { it == digit.toString() }
}

private fun getAnimationSpec(): TweenSpec<Float> {
    return tween(ANIMATION_DURATION_IN_MILLIS, easing = EaseOutExpo)
}

@Preview
@Composable
private fun DigitWheelPreview() {
    DigitWheel(digit = '9', canAnimate = false)
}