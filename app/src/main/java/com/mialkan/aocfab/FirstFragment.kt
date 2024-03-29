package com.mialkan.aocfab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FirstScreen(findNavController())
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    AOCFabTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "FAB Focus Issue",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.semantics { heading() }
                    )
                })
            }

        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Box(modifier = Modifier.padding(20.dp)) {
                    Button(
                        onClick = { navController.navigate(R.id.action_FirstFragment_to_SecondFragment) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Next Screen")
                    }
                }
            }
        }
    }
}
