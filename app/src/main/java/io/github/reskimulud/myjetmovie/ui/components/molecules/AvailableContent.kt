/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.components.molecules

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import io.github.reskimulud.myjetmovie.domain.model.Movie

@Composable
fun AvailableContent(
    movies: List<Movie>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(movies, key = { it.id }) { movie ->
             MovieItem(movie, navController, scaffoldState, onUpdateFavoriteMovie)
        }
    }
}