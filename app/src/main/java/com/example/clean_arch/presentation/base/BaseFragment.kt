package com.example.clean_arch.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import com.example.clean_arch.di.core.CoreComponent
import com.example.clean_arch.presentation.util.Event
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.*

abstract class BaseFragment<VM : ViewModel>(@LayoutRes private val resId: Int) : Fragment() {

    lateinit var activity: BaseActivity

    lateinit var viewModel: VM

    protected abstract fun createViewModel(): VM

    protected abstract fun injectComponent(coreComponent: CoreComponent)

    protected abstract fun init()

    protected abstract fun observeViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context as BaseActivity
        activity = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent(activity.coreComponent)
        viewModel = createViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        init()
    }

    fun <T> LiveData<Event<T>>.observe(observer: (T) -> Unit) {
        observe(this@BaseFragment, Observer {
            it?.getContentIfNotHandled()?.let(observer)
        })
    }
}
