package com.tomdroid.interview.idme.ui.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.tomdroid.interview.idme.ui.components.PrimaryButtonComponent
import com.tomdroid.interview.idme.ui.components.TitleDetailTextComponent
import com.tomdroid.interview.idme.util.xAsSimpleDateStringOrNull
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val vm: ProfileVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val uiState = vm.uiStateFlow().collectAsState().value
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    uiState?.let {
                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 56.dp)
                                .clip(CircleShape)
                                .height(192.dp)
                                .width(192.dp),
                            model = uiState.imageUrl,
                            contentDescription = "${uiState.fullName}'s profile image",
                        )
                        Text(
                            text = uiState.name,
                            fontSize = TextUnit(32f, TextUnitType.Sp),
                            fontWeight = FontWeight.Bold
                        )
                        TitleDetailTextComponent(title = "Username", detail = uiState.userName)
                        TitleDetailTextComponent(title = "Full name", detail = uiState.fullName)
                        TitleDetailTextComponent(title = "Phone number", detail = uiState.phoneNumber)
                        TitleDetailTextComponent(title = "Registration Date", detail = uiState.registration.xAsSimpleDateStringOrNull() ?: "???")
                        PrimaryButtonComponent(
                            text = "Purchase History",
                            onClick = {
                                vm.onViewPurchasesClicked()
                            }
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

}