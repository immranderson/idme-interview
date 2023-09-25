package com.tomdroid.interview.idme.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tomdroid.interview.idme.data.networking.services.ProfileService
import com.tomdroid.interview.idme.data.networking.services.PurchasesService
import com.tomdroid.interview.idme.data.repositories.ProfileRepo
import com.tomdroid.interview.idme.data.repositories.ProfileRepoImpl
import com.tomdroid.interview.idme.data.repositories.PurchasesRepo
import com.tomdroid.interview.idme.data.repositories.PurchasesRepoImpl
import com.tomdroid.interview.idme.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Provides
    @Singleton
    fun providePurchasesService(retrofit: Retrofit): PurchasesService {
        return retrofit.create(PurchasesService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepo(
        profileService: ProfileService
    ): ProfileRepo {
        return ProfileRepoImpl(
            profileService = profileService
        )
    }

    @Provides
    @Singleton
    fun providePurchasesRepo(
        purchasesService: PurchasesService
    ): PurchasesRepo {
        return PurchasesRepoImpl(
            purchasesService = purchasesService
        )
    }

}