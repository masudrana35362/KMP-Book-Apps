package org.masud.bookapp

import androidx.compose.ui.window.ComposeUIViewController
import org.masud.bookapp.app.App
import org.masud.bookapp.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }