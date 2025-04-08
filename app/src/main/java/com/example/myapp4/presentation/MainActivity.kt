package com.example.myapp4.presentation

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp4.presentation.screens.AddNoteScreen
import com.example.myapp4.presentation.screens.NotesScreen
import com.example.myapp4.presentation.viewModels.MainActivityViewModel
import com.example.myapp4.ui.theme.myapp4Theme
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.myapp4.presentation.navigation.Directions
import com.example.myapp4.presentation.screens.NoteDetailScreen

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel> {
        MainActivityViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            myapp4Theme {
                val navController = rememberNavController()
                val currentBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = currentBackStackEntry?.destination?.route
                val showFab = currentRoute == Directions.NotesList().route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        if (showFab) {
                            ExtendedFloatingActionButton(
                                onClick = {
                                    navController.navigate(Directions.AddNote().route)
                                },
                                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                                text = { Text(text = "Add a note") }
                            )
                        }
                    }
                ) { innerPadding ->
                    CompositionLocalProvider(LocalNavController provides navController) {
                        NavHost(
                            navController,
                            startDestination = Directions.NotesList().route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(Directions.NotesList().route) {
                                NotesScreen(viewModel)
                            }
                            composable(Directions.AddNote().route) {
                                AddNoteScreen(viewModel = viewModel)
                            }
                            composable(
                                route = Directions.NOTE_DETAIL_ROUTE,
                                arguments = listOf(navArgument("noteId") { type = androidx.navigation.NavType.IntType })
                            ) { backStackEntry ->
                                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                                android.util.Log.d("MainActivity", "Navigated to NoteDetailScreen with noteId: $noteId")
                                NoteDetailScreen(noteId = noteId, viewModel = viewModel)
                            }
                            composable(
                                route = "add_note/{noteId}",
                                arguments = listOf(navArgument("noteId") { type = androidx.navigation.NavType.IntType })
                            ) { backStackEntry ->
                                val noteId = backStackEntry.arguments?.getInt("noteId")
                                Log.d("MainActivity", "Navigated to AddNoteScreen with noteId: $noteId")
                                AddNoteScreen(noteId = noteId, viewModel = viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

val LocalNavController = compositionLocalOf<NavController> {
    throw IllegalArgumentException("No NavController provided")
}

class MainActivityViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}