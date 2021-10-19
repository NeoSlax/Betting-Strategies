package com.example.betting_strategies.framework.presentation.main.multifeature

import androidx.navigation.NavController

interface MainNavController {

    fun navController(): NavController?

    fun setNavController(navController: NavController)

    fun navMain()
}