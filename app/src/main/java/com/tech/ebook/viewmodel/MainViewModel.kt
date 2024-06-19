package com.tech.ebook.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.ebook.model.BookItem
import com.tech.ebook.utils.DetailsViewState
import com.tech.ebook.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainViewModel : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val books = _viewState.asStateFlow()

    private val _detailViewState = MutableStateFlow<DetailsViewState>(DetailsViewState.Loading)
    val detailBooks = _detailViewState.asStateFlow()

    //Helps to formate the JSON
    val formate = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    //get all the data from the Book.json
    fun getAllBooks(context: Context) = viewModelScope.launch {

        try {
            //read Json File
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }
            //formate JSON
            val bookList = formate.decodeFromString<List<BookItem>>(myJson)

            _viewState.value = ViewState.Success(bookList)
        } catch (e: Exception) {
            _viewState.value = ViewState.Error(e)
        }
    }
    //get book by ID

    fun getBookByISBNNo(context: Context, isbnNo: String) = viewModelScope.launch {
        try {
            //read Json File
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }
            //formate JSON
            val book = formate.decodeFromString<List<BookItem>>(myJson)
                .find { it.isbn.contains(isbnNo, ignoreCase = true) }

            _detailViewState.value = DetailsViewState.Success(book!!)
        } catch (e: Exception) {
            _detailViewState.value = DetailsViewState.Error(e)
        }
    }
}