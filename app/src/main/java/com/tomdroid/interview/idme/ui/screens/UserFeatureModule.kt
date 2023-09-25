package com.tomdroid.interview.idme.ui.screens

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserFeatureModule {

    @Provides
    @Singleton
    fun provideUserFeatureState(): UserManager {
        return UserManager()
    }

}