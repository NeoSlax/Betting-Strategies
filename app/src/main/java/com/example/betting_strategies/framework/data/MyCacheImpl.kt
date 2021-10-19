package com.example.betting_strategies.framework.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.betting_strategies.business.data.MyCache
import javax.inject.Inject

class MyCacheImpl @Inject constructor(private var sharedPreferences: SharedPreferences): MyCache {

    override fun getFavoriteIds(): MutableSet<String> {
        val favIds = sharedPreferences.getStringSet(FAVORITE_KEY, null) ?: mutableSetOf<String>()
        Log.d("SharedPreferences", "favIds: ${favIds}")

        return favIds
    }

    override fun addFavorite(id: String) {
        sharedPreferences.let {
            with(sharedPreferences.edit()) {
                clear();
                val set: MutableSet<String> = getFavoriteIds()
                set.add(id)
                Log.d("SharedPreferences", "addFavorite : ${set}")
                putStringSet(FAVORITE_KEY, set);
                apply()
            }
        }
    }

    override fun removeFavorite(id: String) {
        sharedPreferences.let {
            with(sharedPreferences.edit()) {
                clear();
                val set: MutableSet<String> = getFavoriteIds()
                set.remove(id)
                putStringSet(FAVORITE_KEY, set);
                apply()
            }
        }
    }

    companion object {
        val FAVORITE_KEY = "FAVORITE_KEY"
    }
}