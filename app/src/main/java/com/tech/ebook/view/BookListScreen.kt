package com.tech.ebook.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.ebook.R
import com.tech.ebook.components.InputTextField
import com.tech.ebook.components.ItemBookList
import com.tech.ebook.model.BookItem
import com.tech.ebook.navigation.MainActions
import com.tech.ebook.utils.ViewState
import com.tech.ebook.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookListScreen(viewModel: MainViewModel, actions: MainActions) {

    when (val result = viewModel.books.value) {
        ViewState.Empty -> Text(text = "No Result Found")
        is ViewState.Error -> Text(text = "Error Found: ${result.exception}")
        ViewState.Loading -> Text(text = "Loading")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
    }
}

@Composable
fun BookList(bookList: List<BookItem>, actions: MainActions) {

    val search = remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(
            top = 24.dp,
            bottom = 24.dp
        )
    ) {

        //title
        item {
            Text(
                text = stringResource(R.string.explore_thousand_of_books_in_go),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp, bottom = 24.dp)
            )
        }
        //search
        item {
            InputTextField(label = stringResource(R.string.search_for_books), value = search.value) {
                search.value = it
            }
        }
        //Search results title
        item {
            Text(
                text = "Famous books",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp,top = 8.dp)
            )
        }

        //All books list view
        items(bookList.filter { it.title.contains(search.value, ignoreCase = true) }){
            ItemBookList(it.title,it.authors,it.thumbnailUrl,it.categories){
                actions.gotoBookDetails.invoke(it.isbn)
            }
        }
    }
}
