package com.tomdroid.interview.idme.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tomdroid.interview.idme.R
import com.tomdroid.interview.idme.util.xAsSimpleDateStringOrNull

@Composable
fun PurchaseHistoryItemComponent(
    imageUrl: Any?,
    itemName: String,
    description: String,
    price: String,
    purchaseDate: String,
    serial: String
) {

    Column(
        Modifier.padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.wrapContentWidth()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                        .height(40.dp)
                        .width(40.dp),
                    model = imageUrl,
                    contentDescription = "Item Image"
                )

                Column(modifier = Modifier.wrapContentHeight()) {
                    Text(text = itemName, fontWeight = FontWeight.SemiBold)
                    Text(text = purchaseDate.xAsSimpleDateStringOrNull() ?: "???")
                    Text(text = "Serial: $serial")
                }

            }

            Text(text = "\$$price", fontWeight = FontWeight.SemiBold)
        }
        Text(
            modifier = Modifier.padding(start = 56.dp),
            text = "Description:\n$description"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PurchaseHistoryItem_Preview() {
    PurchaseHistoryItemComponent(
        itemName = "Macbook Pro",
        imageUrl = R.drawable.idme_logo,
        description = "Hey this is a long description to demonstrate wrap behavior",
        purchaseDate = "August 10, 2021",
        serial = "83398920",
        price = "1234.99"
    )
}