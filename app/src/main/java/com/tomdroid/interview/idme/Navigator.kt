package com.tomdroid.interview.idme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface Navigator {

    fun setBackStackState(backStackState: ArrayDeque<NavigationTarget>)

    fun navigationTargetBackStackStateFlow(): StateFlow<ArrayDeque<NavigationTarget>>

    fun navigate(route: NavigationTarget)

    fun onBackPressed(): Boolean

    fun isBackStackEmpty(): Boolean

    fun setOnBackStackEmptyListener(onBackStackEmpty: (Boolean) -> Unit)

}

class NavigatorImpl : Navigator {

    private var _backstackEmptyListener: ((Boolean) -> Unit)? = null

    private val _screenBackStackFlow = MutableStateFlow(
        ArrayDeque<NavigationTarget>().apply {

            addFirst(
                NavigationTarget.SplashFragmentTarget
            )
        }
    )


    override fun navigationTargetBackStackStateFlow(): StateFlow<ArrayDeque<NavigationTarget>> {
        return _screenBackStackFlow.asStateFlow()
    }

    override fun navigate(route: NavigationTarget) {

        val backStackStateClone = ArrayDeque(_screenBackStackFlow.value).apply { addFirst(route) }
        _screenBackStackFlow.value = backStackStateClone

    }

    override fun onBackPressed(): Boolean {
        val backStackStateClone = ArrayDeque(_screenBackStackFlow.value).apply { removeFirst() }

        _screenBackStackFlow.value = backStackStateClone
        _backstackEmptyListener?.invoke(_screenBackStackFlow.value.isEmpty())
        return backStackStateClone.isEmpty()
    }

    override fun setBackStackState(backStackState: ArrayDeque<NavigationTarget>) {
        _screenBackStackFlow.value = backStackState
    }

    override fun isBackStackEmpty(): Boolean {
        return _screenBackStackFlow.value.isEmpty()
    }

    override fun setOnBackStackEmptyListener(onBackStackEmpty: (Boolean) -> Unit) {
        _backstackEmptyListener = onBackStackEmpty
    }
}

sealed class NavigationTarget {

    object SplashFragmentTarget : NavigationTarget()
    object ProfileFragmentTarget: NavigationTarget()
    object PurchaseHistoryFragmentTarget: NavigationTarget()

}