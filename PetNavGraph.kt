
package com.example.petcaretracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petcaretracker.ui.screens.*

@Composable
fun PetNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { PetListScreen(navController) }
        composable("add") { AddPetScreen(navController) }
        composable("details/{petId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId")?.toIntOrNull() ?: 0
            PetDetailScreen(navController, petId)
        }
        composable("edit/{petId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId")?.toIntOrNull() ?: 0
            EditPetScreen(navController, petId)
        }
    }
}
