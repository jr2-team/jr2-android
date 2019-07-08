package io.github.jr2team.jr2android.presentation._base._view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VT : ViewModel> : AppCompatActivity(), LifecycleObserver {
    internal lateinit var viewModel: VT
    internal val compositeDisposable = CompositeDisposable()
    abstract val layoutRes: Int

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun initContent() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun subscribeOnRx() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun observeLiveData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        lifecycle.addObserver(this)
    }

    override fun onPause() {
        lifecycle.removeObserver(this)
        compositeDisposable.clear()
        super.onPause()
    }
}