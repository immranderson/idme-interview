package com.tomdroid.interview.idme.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomdroid.interview.idme.Navigator
import com.tomdroid.interview.idme.data.networking.entities.PurchasesApiResponseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PurchaseHistoryInfoVM @Inject constructor(
    private val userManager: UserManager,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<PurchaseHistoryUiState?> = MutableStateFlow(null)
    fun uiStateFlow() = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userManager.getUserStateFlow().collect {
                    _uiStateFlow.value = PurchaseHistoryUiState(
                        purchaseHistoryEntries = it?.purchasesApiResponseEntity ?: emptyList()
                    )
                }
            }
        }
    }

    fun onBackPressed() {
        navigator.onBackPressed()
    }



    data class PurchaseHistoryUiState(
        val purchaseHistoryEntries: List<PurchasesApiResponseEntity>
    )

}