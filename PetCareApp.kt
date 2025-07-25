
package com.example.petcaretracker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.petcaretracker.ui.navigation.PetNavGraph

@Composable
fun PetCareApp() {
    val navController = rememberNavController()
    PetNavGraph(navController = navController)
}
