package com.example.contactos.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider(Dispatchers.IO, Dispatchers.Main) {
    override val Main: CoroutineContext = Unconfined
    override val IO: CoroutineContext = Unconfined
}