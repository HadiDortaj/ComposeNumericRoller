package com.hadi.numericroller

class AnimationTracker {
    var numberOfTrackedInputChanges: Int = 0
        set(value) {
            if (shouldTrackInputChange()) {
                field = value
            }
        }

    val canAnimate: Boolean
        get() = numberOfTrackedInputChanges > 0

    private fun shouldTrackInputChange() = !canAnimate
}