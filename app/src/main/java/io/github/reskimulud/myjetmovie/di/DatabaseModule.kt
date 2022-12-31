/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 30-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.reskimulud.myjetmovie.data.local.room.MovieDatabase
import io.github.reskimulud.myjetmovie.data.local.room.dao.MovieDao
import io.github.reskimulud.myjetmovie.utils.MovieCallback
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    // movie database
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        provider: Provider<MovieDao>
    ): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies.db"
        ).addCallback(
            MovieCallback(context, provider)
        )
            .fallbackToDestructiveMigration()
            .build()

    // movie dao
    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()

}