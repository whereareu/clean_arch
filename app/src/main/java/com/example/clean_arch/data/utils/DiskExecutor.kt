package com.example.clean_arch.data.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskExecutor : Executor {
    private val diskExecutor : Executor = Executors.newSingleThreadExecutor()
    override fun execute(p0: Runnable?) {
        diskExecutor.execute(p0)
    }

}