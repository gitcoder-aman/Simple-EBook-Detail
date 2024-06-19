package com.tech.ebook.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tech.ebook.ui.theme.background
import com.tech.ebook.ui.theme.primary
import com.tech.ebook.ui.theme.text
import com.tech.ebook.ui.theme.typography
import com.tech.ebook.utils.colorShadow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemBookList(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>,
    onItemClick:() -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .background(background)
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(), // This wraps content height
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = thumbnailUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "by ".plus(authors),
                    style = typography.titleMedium,
                    color = text.copy(0.7F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = title, style = typography.titleLarge, color = text)
                Spacer(modifier = Modifier.height(12.dp))

                FlowRow {
                    categories.forEach {
                        ChipView(category = it)
                    }
                }
            }
        }
    }
}

@Composable
fun ChipView(category: String) {
    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(primary.copy(.10F)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            style = typography.bodyMedium,
            color = primary,
            modifier = Modifier.padding(8.dp)
        )
    }
}
