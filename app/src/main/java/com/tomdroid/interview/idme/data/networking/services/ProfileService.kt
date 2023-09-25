package com.tomdroid.interview.idme.data.networking.services

import com.tomdroid.interview.idme.data.networking.entities.ProfileApiResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {

    @GET("/profile/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): ProfileApiResponseEntity

}