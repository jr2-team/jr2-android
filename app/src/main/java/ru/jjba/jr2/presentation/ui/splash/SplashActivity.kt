package ru.jjba.jr2.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseActivity
import ru.jjba.jr2.presentation.ui.main.MainActivity
import ru.jjba.jr2.presentation.viewmodel.splash.SplashViewModel
import ru.jjba.jr2.presentation.viewmodel.util.InjectorUtil

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