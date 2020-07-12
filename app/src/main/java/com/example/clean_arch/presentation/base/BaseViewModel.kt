package com.example.clean_arch.presentation.base

import androidx.lifecycle.ViewModel
import com.aliasadi.clean.presentation.util.DispatchersProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel (
    private val dispatchers : DispatchersProvider
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatchers.getMain() + SupervisorJob()

    fun execute(job : suspend() -> Unit) = launch {
        withContext(dispatchers.getIO()) { job.invoke() }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}