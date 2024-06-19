package com.tech.ebook.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tech.ebook.ui.theme.text
import com.tech.ebook.ui.theme.typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookDetailCard(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
            painter = rememberAsyncImagePainter(model = thumbnailUrl),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp, 124.dp),
        )

            Text(
                text = "by ".plus(authors),
                style = typography.titleMedium,
                color = text.copy(0.7F),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = typography.titleLarge,
                color = text,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))

            FlowRow {
                categories.forEach {
                    ChipView(category = it)
                }
            }
        }
    }
}
