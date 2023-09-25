package com.tomdroid.interview.idme.ui.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tomdroid.interview.idme.ui.components.PurchaseHistoryItemComponent
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class PurchaseHistoryFragment : Fragment() {

    private val vm: PurchaseHistoryVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val uiState = vm.uiStateFlow().collectAsState().value
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text("Purchases")
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = { vm.onBackPressed() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        val dialog = PurchaseHistoryInfoBottomSheetFragment.newInstance()
                                        dialog.show(childFragmentManager, null)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = "Information"
                                    )
                                }
                            },
                        )
                    }
                ) { innerPadding ->
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        items(uiState?.purchaseHistoryEntries ?: emptyList()) { purchaseItem ->
                            PurchaseHistoryItemComponent(
                                imageUrl = purchaseItem.image,
                                itemName = purchaseItem.itemName,
                                description = purchaseItem.description,
                                price = purchaseItem.price,
                                purchaseDate = purchaseItem.purchaseDate,
                                serial = purchaseItem.serial
                            )
                        }

                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): PurchaseHistoryFragment {
            return PurchaseHistoryFragment()
        }
    }

}