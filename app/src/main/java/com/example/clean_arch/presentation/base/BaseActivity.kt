package com.example.clean_arch.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.clean_arch.presentation.App

abstract class BaseActivity(@LayoutRes private val resId: Int) : AppCompatActivity() {

    val coreComponent by lazy { (application as App).getCore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resId)
    }
}