/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.screen.favorite

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
): ViewModel() {
    private val _favoriteMovies = MutableStateFlow<UIState<List<Movie>>>(UIState.Loading)
    val favoriteMovies = _favoriteMovies.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.getFavoriteMovie()
                .catch { error ->
                    _favoriteMovies.value = UIState.Error(error.message.toString())
                }
                .collect { listFavoriteMovie ->
                    _favoriteMovies.value = UIState.Success(listFavoriteMovie)
                }
        }
    }

    fun updateFavoriteMovie(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.updateFavoriteMovie(id, isFavorite)
        }
    }
}