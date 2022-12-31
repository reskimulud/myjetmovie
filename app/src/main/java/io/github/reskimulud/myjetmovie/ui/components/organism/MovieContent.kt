/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.components.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.ui.components.molecules.AvailableContent
import io.github.reskimulud.myjetmovie.ui.components.atoms.EmptyContent
import io.github.reskimulud.myjetmovie.ui.components.molecules.SearchBar

@Composable
fun MovieContent(
    listTourism: List<Movie>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String? = null,
    onQueryChange: ((String) -> Unit)? = null,
    onUpdateFavoriteTourism: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        if (query != null && onQueryChange != null) {
             SearchBar(query = query, onQueryChange = onQueryChange)
        }
        when (listTourism.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(listTourism, navController, scaffoldState, onUpdateFavoriteTourism)
        }
    }
}