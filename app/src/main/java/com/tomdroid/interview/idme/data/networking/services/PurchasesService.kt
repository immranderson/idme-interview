package com.tomdroid.interview.idme.data.networking.services

import com.tomdroid.interview.idme.data.networking.entities.PurchasesApiResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PurchasesService {

    @GET("/purchases/{id}")
    suspend fun getPurchases(
        @Path("id") id: String,
        @Query("page") page: Int = 1,
    ): List<PurchasesApiResponseEntity>

}