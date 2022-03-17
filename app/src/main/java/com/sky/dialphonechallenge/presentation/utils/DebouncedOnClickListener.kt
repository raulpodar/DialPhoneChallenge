package com.sky.dialphonechallenge.presentation.utils

import android.os.SystemClock
import androidx.annotation.VisibleForTesting
import android.view.View
import java.util.WeakHashMap
import kotlin.math.abs

const val DEFAULT_MIN_INTERVAL_IN_MILLIS = 1000L

/**
 * A Debounced OnClickListener
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 *
 * Reference: https://stackoverflow.com/a/16534470/1401164
 */
abstract class DebouncedOnClickListener
/**
 * The one and only constructor
 * @param minimumIntervalInMillis The minimum allowed time between clicks - any click sooner than this after a previous click will be rejected
 */
constructor(private val minimumIntervalInMillis: Long = DEFAULT_MIN_INTERVAL_IN_MILLIS) : View.OnClickListener {

    private val lastClickMap: MutableMap<View, Long>

    /**
     * Implement this in your subclass instead of onClick
     * @param view The view that was clicked
     */
    abstract fun onDebouncedClick(view: View)

    init {
        this.lastClickMap = WeakHashMap()
    }

    override fun onClick(clickedView: View) {
        if (FEATURE_DISABLED.not()) {
            val previousClickTimestamp = lastClickMap[clickedView]
            val currentTimestamp = SystemClock.uptimeMillis()

            lastClickMap[clickedView] = currentTimestamp
            if (previousClickTimestamp == null || abs(currentTimestamp - previousClickTimestamp) > minimumIntervalInMillis) {
                onDebouncedClick(clickedView)
            }
        } else {
            onDebouncedClick(clickedView)
        }
    }

    companion object {
        @VisibleForTesting
        var FEATURE_DISABLED: Boolean = false
    }
}