package com.example.contactos.utils


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider(io: CoroutineDispatcher, main: MainCoroutineDispatcher) {
    open val Main: CoroutineContext by lazy { main }
    open val IO: CoroutineContext by lazy { io }
}