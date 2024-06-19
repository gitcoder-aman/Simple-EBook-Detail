package com.tech.ebook.navigation

import androidx.annotation.StringRes
import com.tech.ebook.R

sealed class Screen(
    val route : String,@StringRes val resourceId : Int
){
    object BookList : Screen("book_list", R.string.text_bookList)
    object Details : Screen("bool_details", R.string.text_bookDetails)
}