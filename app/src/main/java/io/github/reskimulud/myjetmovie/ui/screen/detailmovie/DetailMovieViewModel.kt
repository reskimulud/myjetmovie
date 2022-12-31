/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.screen.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.domain.usecase.movie.MovieUseCase
import io.github.reskimulud.myjetmovie.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
): ViewModel() {
    private val _detailMovie = MutableStateFlow<UIState<Movie>>(UIState.Loading)
    val detailMovie = _detailMovie

    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.getMovieById(id)
                .catch { error ->
                    _detailMovie.value = UIState.Error(error.message.toString())
                }
                .collect { movie ->
                    _detailMovie.value = UIState.Success(movie)
                }
        }
    }

    fun updateFavoriteMovie(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.updateFavoriteMovie(id, isFavorite)
        }
    }
}