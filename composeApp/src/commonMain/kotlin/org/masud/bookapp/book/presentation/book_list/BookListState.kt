package org.masud.bookapp.book.presentation.book_list

import org.masud.bookapp.book.domain.Book
import org.masud.bookapp.core.presentation.UiText


data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

 val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book Title $it",
        imageUrl = "https://test.com",
        authors = listOf("Masud Rana"),
        description = "This is a description of book $it. It is a very interesting book that covers many topics.",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.678952,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3,

        )
}