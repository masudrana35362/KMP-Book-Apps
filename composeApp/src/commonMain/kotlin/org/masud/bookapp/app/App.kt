package org.masud.bookapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.masud.bookapp.book.presentation.SelectedBookViewModel
import org.masud.bookapp.book.presentation.book_list.BookListScreenRoot
import org.masud.bookapp.book.presentation.book_list.BookListViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {

        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            navController.navigate(
                                Route.BookDetails(book.id)
                            )
                        }
                    )
                }

                composable<Route.BookDetails> { entry ->

                   // val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    val args = entry.toRoute<Route.BookDetails>()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Book Details Screen \n" +
                                    "Book ID: ${args.id}",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
            }
        }

    }

}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute =  destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}

