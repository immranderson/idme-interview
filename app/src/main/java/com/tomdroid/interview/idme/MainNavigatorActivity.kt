package com.tomdroid.interview.idme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.tomdroid.interview.idme.ui.screens.ProfileFragment
import com.tomdroid.interview.idme.ui.screens.PurchaseHistoryFragment
import com.tomdroid.interview.idme.ui.screens.SplashFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainNavigatorActivity : FragmentActivity() {

    private val vm: MainNavigatorActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_navigator)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {

                vm.screenBackStackStateFlow()
                    .collectLatest { theCurrentBackStack: ArrayDeque<NavigationTarget> ->

                        val destinationFragment = when (theCurrentBackStack.firstOrNull()) {

                            NavigationTarget.SplashFragmentTarget -> SplashFragment()
                            is NavigationTarget.ProfileFragmentTarget -> ProfileFragment.newInstance()
                            is NavigationTarget.PurchaseHistoryFragmentTarget -> PurchaseHistoryFragment.newInstance()
                            null -> null

                        }


                        if (destinationFragment != null) {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    destinationFragment,
                                    destinationFragment.tag
                                )
                                .commit()

                        }

                    }
            }

        }

    }

    override fun onBackPressed() {

        vm.onBackPressed()

        if (vm.isBackStackEmpty()) {
            super.onBackPressed()
        }
    }
}
