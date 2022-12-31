/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.domain.usecase.movie

import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val repository: IMovieRepository
): MovieUseCase {
    override fun getMovies(): Flow<List<Movie>> =
        repository.getMovies()

    override fun getMovieById(id: Int): Flow<Movie> =
        repository.getMovieById(id)

    override fun getFavoriteMovie(): Flow<List<Movie>> =
        repository.getFavoriteMovie()

    override suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean) =
        repository.updateFavoriteMovieById(id, isFavorite)

    override fun searchMovieByQuery(query: String): Flow<List<Movie>> =
        repository.searchMovieByQuery(query)
}