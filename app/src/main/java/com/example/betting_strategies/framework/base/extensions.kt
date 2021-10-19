package com.example.betting_strategies.framework.base

import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

@MainThread
inline fun <reified Args : NavArgs> Fragment.navArgsSafe() = NavArgsLazy(Args::class) {
    arguments ?: Bundle()
}