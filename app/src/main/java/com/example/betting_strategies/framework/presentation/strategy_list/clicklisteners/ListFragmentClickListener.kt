package com.example.betting_strategies.framework.presentation.strategy_list.clicklisteners

import android.view.View
import com.example.betting_strategies.business.domain.model.DataItem

interface ListFragmentClickListener {
    fun onClickFavorites(dataItem: DataItem)
    fun onClickGoToDetails(dataItem: DataItem)
}