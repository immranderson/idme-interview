package com.tomdroid.interview.idme.data.networking.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PurchasesApiResponseEntity(
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("item_name")
    val itemName: String,
    @SerialName("price")
    val price: String,
    @SerialName("purchase_date")
    val purchaseDate: String,
    @SerialName("serial")
    val serial: String
)