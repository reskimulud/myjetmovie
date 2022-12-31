/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.domain.usecase.movie.MovieUseCase
import io.github.reskimulud.myjetmovie.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
): ViewModel() {
    private val _movies = MutableStateFlow<UIState<List<Movie>>>(UIState.Loading)
    val movies = _movies.asStateFlow()

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.getMovies().collect { movieList ->
                if (movieList.isNotEmpty()) {
                    _movies.value = UIState.Success(movieList)
                }
            }
        }
    }

    fun updateFavoriteMovie(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.updateFavoriteMovie(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
        searchMovie(query)
    }

    private fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.searchMovieByQuery(query)
                .catch { error ->
                    _movies.value = UIState.Error(error.message.toString())
                }
                .collect { movieList ->
                    _movies.value = UIState.Success(movieList)
                }
        }
    }
}