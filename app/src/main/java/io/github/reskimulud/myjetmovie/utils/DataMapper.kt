/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.utils

import io.github.reskimulud.myjetmovie.data.local.entity.MovieEntity
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.utils.Helper.createImageLink

object DataMapper {
    fun MovieEntity.toDomain(): Movie =
        Movie(
            id, title, overview, releaseDate, voteAverage, voteCount, genres, posterPath, backdropPath, isFavorite
        )

    fun List<MovieEntity>.toDomain(): List<Movie> = map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            genres = it.genres,
            posterPath = it.posterPath.createImageLink(),
            backdropPath = it.backdropPath.createImageLink(),
            isFavorite = it.isFavorite
        )
    }
}