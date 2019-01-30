package ru.jjba.jr2.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

abstract class BaseActivity<VT : ViewModel> : AppCompatActivity(), LifecycleObserver {
    internal lateinit var viewModel: VT
    abstract val layoutRes: Int

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun initContent() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun observeData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        lifecycle.addObserver(this)
    }

    override fun onPause() {
        lifecycle.removeObserver(this)
        super.onPause()
    }
}