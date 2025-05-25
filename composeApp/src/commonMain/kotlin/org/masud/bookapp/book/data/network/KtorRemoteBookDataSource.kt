package org.masud.bookapp.book.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.masud.bookapp.book.data.dto.SearchResponseDto
import org.masud.bookapp.book.domain.Book
import org.masud.bookapp.core.data.safeCall
import org.masud.bookapp.core.domain.DataError
import org.masud.bookapp.core.domain.Result


private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
): RemoteBookDataSource {

   override suspend fun searchBooks(
       query: String,
       resultLimit: Int?
   ) : Result<SearchResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(urlString = "$BASE_URL/search.json") {

                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,author_key,author_name,cover_i,cover_edition_key,first_publish_year,ratings_average,ratings_count,number_of_pages_median,edition_count"
                )
            }
        }

    }
}