package com.tomdroid.interview.idme.data.repositories

import com.tomdroid.interview.idme.data.networking.entities.PurchasesApiResponseEntity
import com.tomdroid.interview.idme.data.networking.services.PurchasesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PurchasesRepo {

    fun fetchPurchases(userId: String): Flow<List<PurchasesApiResponseEntity>>

}

class PurchasesRepoImpl(
    private val purchasesService: PurchasesService
): PurchasesRepo {


    override fun fetchPurchases(userId: String): Flow<List<PurchasesApiResponseEntity>> {
        return flow {
            emit(
                purchasesService.getPurchases(
                    id = userId
                )
            )
        }
    }
}