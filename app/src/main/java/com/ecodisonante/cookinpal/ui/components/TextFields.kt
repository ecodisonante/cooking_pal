package com.ecodisonante.cookinpal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp)
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        shape = shape,
        modifier = modifier,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
    )
}


@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    customHeight: Int = 450,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .width(260.dp)
            .height(customHeight.dp)
            .padding(top = 50.dp)
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xDDFFFFFF),
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            content()
        }
    }
}
