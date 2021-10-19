package com.example.betting_strategies.framework.di

import com.example.betting_strategies.framework.di.main.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class SubComponentsModule