package com.example.clean_arch.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.clean_arch.presentation.di.DaggerInjector

abstract class BaseActivity<VM : ViewModel>(private val contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    protected val daggerInjector by lazy { application as DaggerInjector }

    lateinit var viewModel: VM

    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        setContentView(contentLayoutId)
    }

    fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(this@BaseActivity, Observer {
            it?.let { observer(it) }
        })
    }
}