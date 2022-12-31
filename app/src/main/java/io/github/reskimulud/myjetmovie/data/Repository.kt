/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.data

import io.github.reskimulud.myjetmovie.data.local.LocalDataSource
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.domain.repository.IMovieRepository
import io.github.reskimulud.myjetmovie.utils.DataMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
) : IMovieRepository {
    override fun getMovies(): Flow<List<Movie>> {
        val data = localDataSource.getMovies()
        return data.map {
            it.toDomain()
        }
    }

    override fun getMovieById(id: Int): Flow<Movie> {
        val data = localDataSource.getMovieById(id)
        return data.map {
            it.toDomain()
        }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        val data = localDataSource.getFavoriteMovie()
        return data.map {
            it.toDomain()
        }
    }

    override suspend fun updateFavoriteMovieById(id: Int, isFavorite: Boolean)  =
        localDataSource.updateFavoriteMovieById(id, isFavorite)

    override fun searchMovieByQuery(query: String): Flow<List<Movie>> {
        val data = localDataSource.searchMovieByQuery(query)
        return data.map {
            it.toDomain()
        }
    }
}