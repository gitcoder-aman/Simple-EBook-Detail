package com.tech.ebook.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.ebook.R
import com.tech.ebook.ui.theme.card
import com.tech.ebook.ui.theme.primary
import com.tech.ebook.ui.theme.text
import com.tech.ebook.ui.theme.typography

@Composable
fun LabelView(title: String) {
    Text(
        text = title,
        style = typography.titleMedium,
        textAlign = TextAlign.Start,
        color = text
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    label: String, value: String, onValueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp, end = 20.dp
            )
        , label = { LabelView(title = label)},
        textStyle = typography.bodyLarge,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = primary,
            focusedLabelColor = primary,
            focusedTextColor = text,
            unfocusedIndicatorColor = Color.LightGray,
            cursorColor = primary,
            focusedPlaceholderColor = card,
            disabledPlaceholderColor = card

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}
