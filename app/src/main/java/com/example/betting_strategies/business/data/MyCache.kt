package com.example.betting_strategies.business.data

interface MyCache {

    fun getFavoriteIds(): MutableSet<String>

    fun addFavorite(id: String)

    fun removeFavorite(id: String)
}