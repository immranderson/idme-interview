package com.tomdroid.interview.idme.ui.screens

import com.tomdroid.interview.idme.data.networking.entities.ProfileApiResponseEntity
import com.tomdroid.interview.idme.data.networking.entities.PurchasesApiResponseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserManager {

    private val _userState: MutableStateFlow<ProfileAndPurchases?> = MutableStateFlow(null)

    fun getUserStateFlow() = _userState.asStateFlow()

    fun setUserState(profileAndPurchases: ProfileAndPurchases) {
        _userState.value = profileAndPurchases
    }

    data class ProfileAndPurchases(
        val profileApiResponseEntity: ProfileApiResponseEntity,
        val purchasesApiResponseEntity: List<PurchasesApiResponseEntity>
    )

}