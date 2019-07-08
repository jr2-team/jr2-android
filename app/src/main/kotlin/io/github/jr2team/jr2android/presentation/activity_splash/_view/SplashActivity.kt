package io.github.jr2team.jr2android.presentation.activity_splash._view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseActivity
import io.github.jr2team.jr2android.common.extensions.subscribe
import io.github.jr2team.jr2android.presentation.activity_splash._viewmodel.SplashViewModel
import io.github.jr2team.jr2android.presentation.activity_splash._viewmodel.SplashState
import io.github.jr2team.jr2android.common.dependency_injection.ViewModelInjectorUtil

class SplashActivity : BaseActivity<SplashViewModel>() {
    override val layoutRes = R.layout.fragment_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelInjectorUtil.provideSplashViewModel()
        viewModel = ViewModelProviders.of(this, factory)[SplashViewModel::class.java]
    }

    override fun subscribeOnRx() = with(compositeDisposable) {
        subscribe(viewModel.statePublisher) { state ->
            when (state) {
                is SplashState.NavigateToMainActivity -> {
                    val destination = Intent(this@SplashActivity, io.github.jr2team.jr2android.presentation.activity_main._view.MainActivity::class.java)
                    navigateTo(destination)
                }
            }
        }
    }

    private fun navigateTo(intent: Intent) {
        startActivity(intent)
        finish()
    }
}