package org.masud.bookapp.book.presentation.book_details

import org.masud.bookapp.book.domain.Book

data class BookDetailState (
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null

)