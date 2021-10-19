package com.example.betting_strategies.business.data

import com.example.betting_strategies.business.domain.model.DataItem
import java.lang.Exception

object DataFactory {

    private val list = listOf<DataItem>(
        DataItem(
            id = "id1",
            name = "name1",
            description = "description1",
            imageUrl = "https://via.placeholder.com/600/51aa97",
            isFavorite = false
        ),
        DataItem(
            id = "id2",
            name = "name2",
            description = "description2",
            imageUrl = "https://via.placeholder.com/600/92c952",
            isFavorite = false
        ),
        DataItem(
            id = "id3",
            name = "name3",
            description = "description3",
            imageUrl = "https://via.placeholder.com/600/771796",
            isFavorite = false
        )
    )

    fun getData(): List<DataItem> = list

    fun getDataByNumber(number: Int ) = try {
        list[number]
    } catch (e: Exception) {
        list[0]
    }

}