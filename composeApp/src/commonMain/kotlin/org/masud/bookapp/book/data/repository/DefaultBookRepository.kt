package org.masud.bookapp.book.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.masud.bookapp.book.data.database.FavoriteBookDao
import org.masud.bookapp.book.data.mappers.toBook
import org.masud.bookapp.book.data.mappers.toBookEntity
import org.masud.bookapp.book.data.network.RemoteBookDataSource
import org.masud.bookapp.book.domain.Book
import org.masud.bookapp.book.domain.BookRepository
import org.masud.bookapp.core.domain.DataError
import org.masud.bookapp.core.domain.EmptyResult
import org.masud.bookapp.core.domain.Result
import org.masud.bookapp.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }

            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {

        val localResult = favoriteBookDao.getFavoriteBookById(bookId)

       return if (localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }

    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map {
                    it.toBook()
                }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFavoriteBook(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }
}