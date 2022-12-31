/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.screen.home

import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.reskimulud.myjetmovie.ui.components.atoms.ErrorContent
import io.github.reskimulud.myjetmovie.ui.components.atoms.LoadingIndicator
import io.github.reskimulud.myjetmovie.ui.components.organism.MovieContent
import io.github.reskimulud.myjetmovie.utils.UIState

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.movies.collectAsState(UIState.Loading).value.let { listUIState ->
        Log.e("HomeScreen", listUIState.toString())
        when (listUIState) {
            is UIState.Loading -> LoadingIndicator()
            is UIState.Error -> ErrorContent()
            is UIState.Success -> {
                MovieContent(
                    listMovie = listUIState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteTourism = homeViewModel::updateFavoriteMovie,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange
                )
            }
        }
    }
}