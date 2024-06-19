package com.tech.ebook.utils

import com.tech.ebook.model.BookItem

sealed class DetailsViewState {
    object Empty : DetailsViewState()
    object Loading : DetailsViewState()
    data class Success(val data : BookItem):DetailsViewState()
    data class Error(val exception : Throwable):DetailsViewState()
}