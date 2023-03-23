package com.mialkan.aocfab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FABScreen(findNavController())
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FABScreen(navController: NavController) {
    val showLoadingDuration by remember { mutableStateOf(1500) }
    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = showLoading) {
        if (showLoading) {
            delay(showLoadingDuration.toLong())
            showLoading = false
        }
    }
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
                }, navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Navigate Up"
                            )
                        }
                    })
            },
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                if (!showLoading) {
                    FloatingActionButton(
                        modifier = Modifier.size(68.dp),
                        onClick = {
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.new_content),
                            contentDescription = "Add new content"
                        )
                    }
                }
            }
        ) { paddingValues ->
            if (showLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            } else {
                Box(modifier = Modifier.padding(paddingValues)) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().background(Color.LightGray),
                        state = rememberLazyListState()
                    ) {
                        items(10) {
                            Box(modifier = Modifier.padding(10.dp)) {
                                Card(
                                    onClick = {},
                                    modifier = Modifier.fillMaxWidth().height(100.dp)
                                ) {
                                    Text("Card dont try to click")
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
    }
}

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AOCFabTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}
