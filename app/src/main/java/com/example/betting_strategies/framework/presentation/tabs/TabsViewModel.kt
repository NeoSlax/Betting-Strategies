package com.example.betting_strategies.framework.presentation.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.betting_strategies.business.data.DataFactory
import com.example.betting_strategies.business.data.MyCache
import com.example.betting_strategies.business.domain.model.DataItem
import java.util.ArrayList
import javax.inject.Inject

class TabsViewModel @Inject constructor(
    private val myCache: MyCache
) : ViewModel() {

    private var navigator: TabFragmentNavigator? = null

    
    private val objectList : MutableLiveData<List<DataItem?>> = MutableLiveData<List<DataItem?>>()
    private val favObjectList  : LiveData<List<DataItem?>> = Transformations.map(objectList) {
        it.filter { item -> item?.isFavorite == true }
    }

    fun getDataList(isFavorite: Boolean): LiveData<List<DataItem?>> {
        return if (isFavorite) {
            favObjectList
        } else {
            objectList
        }
    }

    fun addToFavorites(data: DataItem) {
        myCache.addFavorite(data.id)
        val updatedList: List<DataItem> = ArrayList(objectList.value ?: emptyList())
        for (item in updatedList) {
            if (item.id == data.id) {
                item.isFavorite = true
            }
        }
        objectList.postValue(updatedList)
    }

    fun removeFromFavorites(data: DataItem) {
        myCache.removeFavorite(data.id)
        val updatedList: List<DataItem> = ArrayList(objectList.value ?: emptyList())
        for (item in updatedList) {
            if (item.id == data.id) {
                item.isFavorite = false
            }
        }
        objectList.postValue(updatedList)
    }

    fun setNavigator(navigator: TabFragmentNavigator) {
        this.navigator = navigator
    }

    fun navigateToDetails(dataItem: DataItem) {
        navigator?.goToDetails(dataItem)
    }

    init {
        val favIds: Set<String> = myCache.getFavoriteIds()
        val arrayList: ArrayList<DataItem> = ArrayList<DataItem>(DataFactory.getData())
        for (item in arrayList) {
            if (favIds.contains(item.id)) {
                item.isFavorite = true
            }
        }
        objectList.postValue(arrayList)
    }
}