package org.masud.bookapp.book.domain

import org.masud.bookapp.core.domain.DataError
import org.masud.bookapp.core.domain.Result

interface BookRepository {

    suspend fun searchBooks(query: String) : Result<List<Book>, DataError.Remote>
}