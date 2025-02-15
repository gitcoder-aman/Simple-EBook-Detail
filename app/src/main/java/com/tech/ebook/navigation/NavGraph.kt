package com.tech.ebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tech.ebook.view.BookDetailScreen
import com.tech.ebook.view.BookListScreen
import com.tech.ebook.viewmodel.MainViewModel
import java.lang.IllegalStateException


object EndPoints {
    const val ID = "id"
}

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.BookList.route) {
        composable(Screen.BookList.route) {
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            viewModel.getAllBooks(context = context)
            BookListScreen(viewModel,actions)
        }
        composable("${Screen.Details.route}/{id}", arguments = listOf(navArgument(EndPoints.ID) {
            type = NavType.StringType
        })) {
            val viewModel = hiltViewModel<MainViewModel>(it)
            val isbnNo = it.arguments?.getString(EndPoints.ID)
                ?: throw IllegalStateException("'Book ISBN No' shouldn't be null")

            viewModel.getBookByISBNNo(context = context, isbnNo = isbnNo)
            BookDetailScreen(viewModel,actions)
        }
    }

}

class MainActions(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val gotoBookDetails: (String) -> Unit = { isbnNo ->
        navController.navigate("${Screen.Details.route}/$isbnNo")
    }
    val gotoBookList: () -> Unit = {
        navController.navigate(Screen.BookList.route)
    }
}