package com.tomdroid.interview.idme

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNavigatorActivityVM @Inject constructor(
    private val navigator: Navigator
): ViewModel() {

    init {
        navigator.setBackStackState(
            backStackState = ArrayDeque<NavigationTarget>().apply {
                addFirst(NavigationTarget.SplashFragmentTarget)
            }
        )
    }

    fun screenBackStackStateFlow() = navigator.navigationTargetBackStackStateFlow()

    fun isBackStackEmpty() = navigator.isBackStackEmpty()

    fun onBackPressed() = navigator.onBackPressed()

}