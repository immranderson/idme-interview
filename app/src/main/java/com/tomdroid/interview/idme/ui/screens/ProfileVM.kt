package com.tomdroid.interview.idme.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomdroid.interview.idme.NavigationTarget
import com.tomdroid.interview.idme.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ProfileVM @Inject constructor(
    private val userManager: UserManager,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<ProfileUiState?> = MutableStateFlow(null)
    fun uiStateFlow() = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                userManager.getUserStateFlow().collect {

                    val profileUiState = ProfileUiState(
                        name = it?.profileApiResponseEntity?.name ?: "Name",
                        fullName = it?.profileApiResponseEntity?.fullName ?: "Full Name",
                        imageUrl = it?.profileApiResponseEntity?.image ?: "",
                        userName = it?.profileApiResponseEntity?.userName ?: "Username",
                        phoneNumber = it?.profileApiResponseEntity?.phoneNumber ?: "+1 800-123-4567",
                        registration = it?.profileApiResponseEntity?.registration ?: "Sometime ago",
                    )

                    _uiStateFlow.value = profileUiState

                }
            }
        }

    }

    fun onViewPurchasesClicked() {
        navigator.navigate(NavigationTarget.PurchaseHistoryFragmentTarget)
    }

    data class ProfileUiState(
        val name: String,
        val fullName: String,
        val imageUrl: String,
        val userName: String,
        val phoneNumber: String,
        val registration: String
    )
}