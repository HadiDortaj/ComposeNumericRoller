package com.hadi.numericroller

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class WheelScrollState {
    companion object {
        private const val DEFAULT_ANIMATION_DURATION_IN_MILLIS = 2000
        private const val INVALID_ITEM_HEIGHT = -1
    }

    private val animatable = Animatable(0f)

    val value: Int
        get() = animatable.value.toInt()

    private var itemHeight: Int = INVALID_ITEM_HEIGHT
    private val isInitialized: Boolean
        get() = itemHeight != INVALID_ITEM_HEIGHT

    fun updateItemHeight(itemHeight: Int) {
        if (!isInitialized) {
            this.itemHeight = itemHeight
        }
    }

    suspend fun animateScrollToIndex(index: Int, animationSpec: AnimationSpec<Float> = getDefaultAnimationSpec()) {
        val targetScrollValue = getTargetScrollValue(index)
        animatable.animateTo(targetScrollValue.toFloat(), animationSpec)
    }

    suspend fun scrollToIndex(index: Int) {
        val targetScrollValue = getTargetScrollValue(index)
        animatable.animateTo(targetScrollValue.toFloat(), animationSpec = snap())
    }

    private fun getTargetScrollValue(index: Int): Int {
        require(isInitialized)
        return -index * itemHeight
    }

    private fun getDefaultAnimationSpec(): TweenSpec<Float> {
        return tween(DEFAULT_ANIMATION_DURATION_IN_MILLIS, easing = EaseOutExpo)
    }

}

@Composable
fun rememberWheelScrollState(): WheelScrollState {
    val wheelScrollState = remember {
        WheelScrollState()
    }
    return wheelScrollState
}