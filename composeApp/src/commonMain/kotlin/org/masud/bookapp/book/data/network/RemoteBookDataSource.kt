package org.masud.bookapp.book.data.network

import org.masud.bookapp.book.data.dto.SearchResponseDto
import org.masud.bookapp.core.domain.DataError
import org.masud.bookapp.core.domain.Result

interface RemoteBookDataSource {

    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}