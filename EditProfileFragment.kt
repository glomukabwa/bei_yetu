package com.example.projectdraft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.projectdraft.ui.theme.ProjectdraftTheme


class EditProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ProjectdraftTheme {
                    Surface {
                        EditProfileScreen(
                            onBackClicked = { parentFragmentManager.popBackStack() },
                            onSaveClicked = { firstName, lastName, email, mobile ->
                                // TODO: handle saving logic
                                parentFragmentManager.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
