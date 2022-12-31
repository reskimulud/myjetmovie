/**
 * Copyright (c) 2022
 * Project  : My Jet Movie
 * Created by Reski Mulud Muchamad on 31-12-2022
 * GitHub   : https://github.com/reskimulud
 * LinkedIn : https://linkedin.com/in/reskimulud
 */

package io.github.reskimulud.myjetmovie.utils

import android.content.Context
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.reskimulud.myjetmovie.R
import io.github.reskimulud.myjetmovie.data.local.entity.MovieEntity
import io.github.reskimulud.myjetmovie.data.local.room.dao.MovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Provider

class MovieCallback(
    private val context: Context,
    private val provider: Provider<MovieDao>
): Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            val dao = provider.get()
            populateData(context, dao)
        }
    }

    private fun populateData(context: Context, dao: MovieDao) {
        val jsonArray = loadJsonArray(context)
        try {
            if (jsonArray != null) {
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    dao.insertAll(
                        MovieEntity(
                            item.getInt("id"),
                            item.getString("title"),
                            item.getString("overview"),
                            item.getString("release_date"),
                            item.getDouble("vote_average"),
                            item.getLong("vote_count"),
                            item.getString("genres"),
                            item.getString("poster_path"),
                            item.getString("backdrop_path")
                        )
                    )
                }
            }
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
    }

    private fun loadJsonArray(context: Context): JSONArray? {
        val builder = StringBuilder()
        val `in` = context.resources.openRawResource(R.raw.movies)
        val reader = BufferedReader(InputStreamReader(`in`))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("movies")
        } catch (exception: IOException) {
            exception.printStackTrace()
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return null
    }
}