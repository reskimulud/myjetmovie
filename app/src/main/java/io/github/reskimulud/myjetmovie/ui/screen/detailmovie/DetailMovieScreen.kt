/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.screen.detailmovie

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import io.github.reskimulud.myjetmovie.R
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.ui.components.atoms.ErrorContent
import io.github.reskimulud.myjetmovie.ui.components.atoms.GenreBadge
import io.github.reskimulud.myjetmovie.ui.components.atoms.TitleRatingAndBtnFav
import io.github.reskimulud.myjetmovie.utils.Helper.createImageLink
import io.github.reskimulud.myjetmovie.utils.UIState

@Composable
fun DetailMovieScreen(
    movieId: Int,
    scaffoldState: ScaffoldState,
) {
    val detailMovieViewModel = hiltViewModel<DetailMovieViewModel>()

    detailMovieViewModel.detailMovie.collectAsState().value.let { listUIState ->
        when (listUIState) {
            is UIState.Loading -> detailMovieViewModel.getMovieById(movieId)
            is UIState.Error -> ErrorContent()
            is UIState.Success -> {
                DetailMovieContent(
                    movie = listUIState.data,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteMovie = detailMovieViewModel::updateFavoriteMovie
                )
            }
        }
    }
}

@Composable
fun DetailMovieContent(
    movie: Movie,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    val (
        _,
        title,
        overview,
        releaseDate,
        voteAverage,
        voteCount,
        genres,
        posterPath,
        backdropPath,
        _,
    ) = movie
    val coroutineScope = rememberCoroutineScope()

    Log.e("DetailMovie", "movie : $movie")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            GenreBadge(text = genres)
            AsyncImage(
                model = backdropPath.createImageLink(),
                contentDescription = title,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.placeholder_image),
                modifier = Modifier.fillMaxWidth()
            )
            AsyncImage(
                model = posterPath.createImageLink(),
                contentDescription = title,
                placeholder = painterResource(id = R.drawable.placeholder_image),
                modifier = Modifier
                    .width(120.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = 16.dp, y = 24.dp)
                    .shadow(12.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TitleRatingAndBtnFav(
            movie = movie,
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState,
            onUpdateFavoriteMovie = onUpdateFavoriteMovie
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Rating: $voteAverage/$voteCount",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Release: $releaseDate",
                    style = MaterialTheme.typography.caption
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = overview,
                style = MaterialTheme.typography.body1
            )
        }
    }
}