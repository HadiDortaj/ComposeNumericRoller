package com.hadi.numberwheel

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DigitWheel(digit: Char, canAnimate: Boolean, modifier: Modifier = Modifier) {
    val wheelScrollState = rememberWheelScrollState()
    LaunchedEffect(key1 = digit, key2 = canAnimate) {
        val index = getDigitIndex(digit)
        if (canAnimate) {
            wheelScrollState.animateScrollToIndex(index)
        } else {
            wheelScrollState.scrollToIndex(index)
        }
    }

    Layout(measurePolicy = { measurables, constraints ->
        val placeableList = measurables.map {
            val placeable = it.measure(constraints)
            placeable
        }
        val maxMeasuredHeight = placeableList.map { it.height }.max()
        val maxMeasuredWidth = placeableList.map { it.width }.max()

        wheelScrollState.updateItemHeight(maxMeasuredHeight)

        layout(maxMeasuredWidth, maxMeasuredHeight) {
            var height = wheelScrollState.value
            placeableList.forEach {
                it.placeRelative(
                        (maxMeasuredWidth - it.width) / 2,
                        height + ((maxMeasuredHeight - it.height) / 2)
                )
                height += maxMeasuredHeight
            }
        }
    }, content = {
        getWheelItems().forEach { character ->
            Text(
                    text = character, fontSize = 60.sp,
                    modifier = Modifier,
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
    }, modifier = modifier.clipToBounds())
}

private fun getWheelItems(): List<String> {
    return listOf("", *(0..9).map { it.toString() }.toTypedArray())
}

private fun getDigitIndex(digit: Char): Int {
    return getWheelItems().indexOfFirst { it == digit.toString() }
}

@Preview(showBackground = true)
@Composable
private fun DigitWheelPreview() {
    DigitWheel(digit = '8', canAnimate = false)
}