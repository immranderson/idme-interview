package com.tomdroid.interview.idme.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButtonComponent(
    onClick: (() -> Unit)? = null,
    text: String = "Click Me"
) {
    Button(
        onClick = { onClick?.invoke() },

        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0B84FF)
        )

    ) {
        Text(text = text)
    }
}

@Composable
@Preview
fun PrimaryButton_Preview() {
    PrimaryButtonComponent()
}