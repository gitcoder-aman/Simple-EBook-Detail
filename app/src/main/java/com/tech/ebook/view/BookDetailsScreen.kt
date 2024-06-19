package com.tech.ebook.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tech.ebook.R
import com.tech.ebook.components.BookDetailCard
import com.tech.ebook.components.TopBar
import com.tech.ebook.navigation.MainActions
import com.tech.ebook.ui.theme.typography
import com.tech.ebook.utils.DetailsViewState
import com.tech.ebook.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookDetailScreen(viewModel: MainViewModel, actions: MainActions) {

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.text_bookDetails), action = actions)
        }
    ) {
        BookDetails(viewModel = viewModel, paddingValue = it)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookDetails(viewModel: MainViewModel, paddingValue: PaddingValues) {
    when (val result = viewModel.detailBooks.value) {
        DetailsViewState.Empty -> Text(text = "No Result Found!")
        is DetailsViewState.Error -> Text(text = "Error Fund ${result.exception}")
        DetailsViewState.Loading -> Text(text = "Loading")
        is DetailsViewState.Success -> {

            LazyColumn(
                modifier = Modifier.padding(paddingValue)
            ) {
                val book = result.data

                //Book Detail card
                item {

                    BookDetailCard(
                        book.title,
                        book.authors,
                        book.thumbnailUrl,
                        book.categories
                    )
                }
                //Description
                item {
                    Text(
                        text = stringResource(id = R.string.text_bookDetails),
                        style = typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Text(
                        text = book.longDescription,
                        style = typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary.copy(0.7F),
                        modifier = Modifier.padding(16.dp)
                    )

                }
            }
        }
    }
}