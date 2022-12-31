/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.utils

sealed class UIState<out T: Any?> {
    object Loading : UIState<Nothing>()
    data class Success<out T: Any>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}