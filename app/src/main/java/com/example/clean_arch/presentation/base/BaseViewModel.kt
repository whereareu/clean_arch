package com.example.clean_arch.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aliasadi.clean.presentation.util.DispatchersProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel (
    private val dispatchers : DispatchersProvider
) : ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(dispatchers.getMain() + viewModelJob)

    private val ioScope = CoroutineScope(dispatchers.getIO() + viewModelJob)

    fun execute(job : suspend() -> Unit) = ioScope.launch {
            withContext(dispatchers.getIO()) { job.invoke() }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        Log.d("viewModel", "onCleared")
        super.onCleared()
        //Canceling a job when the ViewModel is being finished .
        viewModelJob.cancel()
    }
}