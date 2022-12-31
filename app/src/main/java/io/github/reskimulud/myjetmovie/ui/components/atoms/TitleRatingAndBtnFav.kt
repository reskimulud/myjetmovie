/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.ui.components.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.reskimulud.myjetmovie.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.round

@Composable
fun TitleRatingAndBtnFav(
    movie: Movie,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
    isSmall: Boolean = false,
) {
    val (id, title, _, _, voteAverage, _, _, _, _, isFavorite) = movie
    var padding = 14
    var fontSize = MaterialTheme.typography.h6
    var ratingIconSize = 18
    var btnFavSize = 32

    if (isSmall) {
        padding = 16
        fontSize = MaterialTheme.typography.subtitle2
        ratingIconSize = 14
        btnFavSize = 24
    }

    Row(
        modifier = Modifier.padding(padding.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = fontSize
            )
            Row {
                val nStar = round(voteAverage / 2.0)
                repeat(nStar.toInt()) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = title,
                        tint = Color(0xFFFFCC00),
                        modifier = Modifier.size(ratingIconSize.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Icon(
            imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            tint = if (isFavorite) Color.Red else MaterialTheme.colors.onSurface,
            contentDescription = title,
            modifier = Modifier
                .size(btnFavSize.dp)
                .clip(RoundedCornerShape(100))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (onUpdateFavoriteMovie != null) {
                        onUpdateFavoriteMovie(id, !isFavorite)
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "$title ${if (isFavorite) "removed from" else "added as"} favorite ",
                                actionLabel = "Dismiss",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
        )
    }
}