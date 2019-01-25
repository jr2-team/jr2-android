package ru.jjba.jr2.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseActivity
import ru.jjba.jr2.presentation.ui.main.MainActivity
import ru.jjba.jr2.presentation.viewmodel.splash.SplashViewModel

class SplashActivity : BaseActivity<SplashViewModel>() {
    override var viewModel = SplashViewModel()
    override val layoutRes = R.layout.fragment_splash

    override fun initContent() {
        //viewModel.onSetupDb()
    }

    override fun observeData() {
        viewModel.observeIsAllowedToNavToMain().observe(this@SplashActivity, Observer { isAllowedToNav ->
            if (isAllowedToNav) {
                navigateTo(Intent(this@SplashActivity, MainActivity::class.java))
            }
        })
    }

    private fun navigateTo(intent: Intent) {
        startActivity(intent)
        finish()
    }
}