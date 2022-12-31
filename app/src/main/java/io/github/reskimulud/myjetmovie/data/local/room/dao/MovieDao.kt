/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.reskimulud.myjetmovie.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("UPDATE movies SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteMovieById(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun searchMovieByQuery(query: String): Flow<List<MovieEntity>>
}