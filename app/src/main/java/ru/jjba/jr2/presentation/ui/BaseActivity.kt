package ru.jjba.jr2.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

abstract class BaseActivity<VT : ViewModel> : AppCompatActivity(), LifecycleObserver {
    abstract var viewModel: VT
    abstract val layoutRes: Int

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun initContent() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        viewModel = ViewModelProviders.of(this).get(viewModel::class.java)

        lifecycle.addObserver(this)
    }
}