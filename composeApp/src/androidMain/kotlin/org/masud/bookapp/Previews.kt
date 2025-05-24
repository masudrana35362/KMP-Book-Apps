package org.masud.bookapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.masud.bookapp.book.presentation.book_list.BookListScreen
import org.masud.bookapp.book.presentation.book_list.BookListState
import org.masud.bookapp.book.presentation.book_list.books


@Preview
@Composable
fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {

        }
    )
}