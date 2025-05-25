package org.masud.bookapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.masud.bookapp.book.data.network.KtorRemoteBookDataSource
import org.masud.bookapp.book.data.repository.DefaultBookRepository
import org.masud.bookapp.book.presentation.book_list.BookListScreenRoot
import org.masud.bookapp.book.presentation.book_list.BookListViewModel
import org.masud.bookapp.core.data.HttpClientFactory

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember {
            BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(httpClient = HttpClientFactory.create(engine))
                )
            )
        },
        onBookClick = {

        }
    )

}

