package org.masud.bookapp.core.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.masud.bookapp.book.data.database.DatabaseFactory
import org.masud.bookapp.book.data.database.FavoriteBookDatabase
import org.masud.bookapp.book.data.network.KtorRemoteBookDataSource
import org.masud.bookapp.book.data.network.RemoteBookDataSource
import org.masud.bookapp.book.data.repository.DefaultBookRepository
import org.masud.bookapp.book.domain.BookRepository
import org.masud.bookapp.book.presentation.SelectedBookViewModel
import org.masud.bookapp.book.presentation.book_details.BookDetailViewModel
import org.masud.bookapp.book.presentation.book_list.BookListViewModel
import org.masud.bookapp.core.data.HttpClientFactory

expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<FavoriteBookDatabase>().favoriteBookDao
    }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}