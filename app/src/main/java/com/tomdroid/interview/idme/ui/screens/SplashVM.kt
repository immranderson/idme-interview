package com.tomdroid.interview.idme.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomdroid.interview.idme.NavigationTarget
import com.tomdroid.interview.idme.Navigator
import com.tomdroid.interview.idme.data.repositories.ProfileRepo
import com.tomdroid.interview.idme.data.repositories.PurchasesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val navigator: Navigator,
    private val profileRepo: ProfileRepo,
    private val purchasesRepo: PurchasesRepo,
    private val userManager: UserManager
) : ViewModel() {


    fun fetchInitialSetupForLaunch() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchProfileAndPurchases().collect {

                    userManager.setUserState(
                        UserManager.ProfileAndPurchases(
                            profileApiResponseEntity = it.profileApiResponseEntity,
                            purchasesApiResponseEntity = it.purchasesApiResponseEntity
                        )
                    )

                    navigator.setBackStackState(
                        backStackState = ArrayDeque<NavigationTarget>().apply {
                            addFirst(
                                NavigationTarget.ProfileFragmentTarget
                            )
                        }
                    )
                }
            }
        }
    }

    private fun fetchProfileAndPurchases(): Flow<UserManager.ProfileAndPurchases> {
        return combine(
            profileRepo.fetchProfile("U13023932"),
            purchasesRepo.fetchPurchases("U13023932")
        ) { profiles, purchases ->

            UserManager.ProfileAndPurchases(
                profileApiResponseEntity = profiles,
                purchasesApiResponseEntity = purchases
            )

        }
    }



}