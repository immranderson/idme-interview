package com.tomdroid.interview.idme.data.repositories

import com.tomdroid.interview.idme.data.networking.entities.ProfileApiResponseEntity
import com.tomdroid.interview.idme.data.networking.services.ProfileService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ProfileRepo {

    fun fetchProfile(userId: String): Flow<ProfileApiResponseEntity>

}

class ProfileRepoImpl(
    private val profileService: ProfileService
): ProfileRepo {

    override fun fetchProfile(userId: String): Flow<ProfileApiResponseEntity> {
        return flow {
            emit(
                profileService.getProfile(
                    id = userId
                )
            )
        }
    }
}