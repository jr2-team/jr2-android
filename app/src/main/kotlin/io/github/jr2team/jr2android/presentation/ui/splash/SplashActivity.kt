package io.github.jr2team.jr2android.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseActivity
import io.github.jr2team.jr2android.presentation.ui.main.MainActivity
import io.github.jr2team.jr2android.presentation.viewmodel.splash.SplashViewModel
import io.github.jr2team.jr2android.presentation.viewmodel.util.InjectorUtil

class SplashActivity : BaseActivity<SplashViewModel>() {
    override val layoutRes = R.layout.fragment_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
                this,
                InjectorUtil.provideSplashViewModel()
        )[SplashViewModel::class.java]
    }

    override fun observeData() {
        viewModel.observeIsAllowedToNavToMain().observe(this, Observer { isAllowedToNav ->
            if (isAllowedToNav) {
                navigateTo(Intent(this, MainActivity::class.java))
            }
        })
    }

    private fun navigateTo(intent: Intent) {
        startActivity(intent)
        finish()
    }
}