package org.masud.bookapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.masud.bookapp.book.presentation.book_list.BookListScreen
import org.masud.bookapp.book.presentation.book_list.BookListState



@Preview
@Composable
fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = emptyList()
        ),
        onAction = {

        }
    )
}