package com.example.betting_strategies.framework.di.main

import com.example.betting_strategies.framework.presentation.main.multifeature.MainNavHostFragment
import dagger.Subcomponent

@MainFragmentScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        MainFragmentBuildersModule::class
    ]
)
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): MainComponent
    }

    fun inject(mainNavHostFragment: MainNavHostFragment)

}