/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.components.molecules

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.reskimulud.myjetmovie.R
import io.github.reskimulud.myjetmovie.domain.model.Movie
import io.github.reskimulud.myjetmovie.ui.components.atoms.GenreBadge
import io.github.reskimulud.myjetmovie.ui.components.atoms.TitleRatingAndBtnFav
import io.github.reskimulud.myjetmovie.ui.navigation.Screen

@Composable
fun MovieItem(
    movie: Movie,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val (id, title, _, _, _, _, genres, posterPath, _, _) = movie

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .border(1.dp, Color.LightGray.copy(0.5f), MaterialTheme.shapes.small)
            .clickable { navController.navigate(Screen.Detail.createRoute(id)) },
    ) {
        Column {
            Box {
                AsyncImage(
                    model = posterPath,
                    contentDescription = title,
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.placeholder_image),
                    modifier = Modifier.fillMaxWidth()
                )
                GenreBadge(genres)
            }

            TitleRatingAndBtnFav(
                movie = movie,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState,
                onUpdateFavoriteMovie = onUpdateFavoriteMovie,
                isSmall = true
            )
        }
    }
}