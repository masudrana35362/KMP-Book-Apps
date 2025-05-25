package org.masud.bookapp.book.presentation.book_list

import org.masud.bookapp.book.domain.Book
import org.masud.bookapp.core.presentation.UiText


data class BookListState(
    val searchQuery: String = "kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
