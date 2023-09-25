package com.tomdroid.interview.idme.data.networking.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileApiResponseEntity(
    @SerialName("name") val name: String,
    @SerialName("user_name") val userName: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("registration") val registration: String,
    @SerialName("image") val image: String,
)