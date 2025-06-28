package com.alma.pokedex.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.alma.pokedex.presentation.pokemon_detail.PokemonDetailScreen
import com.alma.pokedex.presentation.pokedex.PokemonListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.POKEMON_LIST
    ) {
        composable(route = NavigationRoutes.POKEMON_LIST) {
            PokemonListScreen(
                onPokemonClick = { name ->
                    navController.navigate(NavigationRoutes.pokemonDetailRoute(name))
                }
            )
        }

        composable(
            route = "${NavigationRoutes.POKEMON_DETAIL}/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: return@composable
            PokemonDetailScreen(pokemonName = name)
        }
    }
}

object NavigationRoutes {
    const val POKEMON_LIST = "pokemon_list"
    const val POKEMON_DETAIL = "pokemon_detail"

    fun pokemonDetailRoute(name: String): String {
        return "$POKEMON_DETAIL/$name"
    }
}