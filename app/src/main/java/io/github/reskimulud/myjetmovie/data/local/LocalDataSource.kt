/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.data.local

import io.github.reskimulud.myjetmovie.data.local.entity.MovieEntity
import io.github.reskimulud.myjetmovie.data.local.room.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: MovieDao,
) {
    fun getMovies(): Flow<List<MovieEntity>> =
        dao.getMovies()

    fun getMovieById(id: Int): Flow<MovieEntity> =
        dao.getMovieById(id)

    fun getFavoriteMovie(): Flow<List<MovieEntity>> =
        dao.getFavoriteMovie()

    suspend fun updateFavoriteMovieById(id: Int, isFavorite: Boolean) =
        dao.updateFavoriteMovieById(id, isFavorite)

    fun searchMovieByQuery(query: String): Flow<List<MovieEntity>> =
        dao.searchMovieByQuery(query)
}