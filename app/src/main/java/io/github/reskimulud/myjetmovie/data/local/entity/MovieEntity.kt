/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @NonNull
    @ColumnInfo(name = "title")
    val title: String,

    @NonNull
    @ColumnInfo(name = "overview")
    val overview: String,

    @NonNull
    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @NonNull
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @NonNull
    @ColumnInfo(name = "vote_count")
    val voteCount: Long,

    @NonNull
    @ColumnInfo(name = "genres")
    val genres: String,

    @NonNull
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @NonNull
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)