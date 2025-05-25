package org.masud.bookapp.book.data.repository

import org.masud.bookapp.book.data.mappers.toBook
import org.masud.bookapp.book.data.network.RemoteBookDataSource
import org.masud.bookapp.book.domain.Book
import org.masud.bookapp.book.domain.BookRepository
import org.masud.bookapp.core.domain.DataError
import org.masud.bookapp.core.domain.Result
import org.masud.bookapp.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
   override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }

            }
    }
}