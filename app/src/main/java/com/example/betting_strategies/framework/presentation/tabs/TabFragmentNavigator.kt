package com.example.betting_strategies.framework.presentation.tabs

import com.example.betting_strategies.business.domain.model.DataItem

/**
 * Defines the navigation actions that can be called from tab screen ViewModel.
 */
interface TabFragmentNavigator {
    fun goToDetails(dataItem: DataItem)
}