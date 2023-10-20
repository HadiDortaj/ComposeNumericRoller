package com.hadi.animatingprice

class AnimationTracker {

    var numberOfTrackedInputChanges: Int = 0
        set(value) {
            if (shouldTrackInputChange()) {
                field = value
            }
        }

    val shouldAnimate: Boolean
        get() = numberOfTrackedInputChanges > 0

    private fun shouldTrackInputChange() = !shouldAnimate
}