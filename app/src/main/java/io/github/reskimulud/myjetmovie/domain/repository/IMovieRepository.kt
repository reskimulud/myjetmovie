/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.domain.repository

import io.github.reskimulud.myjetmovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun getMovieById(id: Int): Flow<Movie>
    fun getFavoriteMovie(): Flow<List<Movie>>
    suspend fun updateFavoriteMovieById(id: Int, isFavorite: Boolean)
    fun searchMovieByQuery(query: String): Flow<List<Movie>>
}