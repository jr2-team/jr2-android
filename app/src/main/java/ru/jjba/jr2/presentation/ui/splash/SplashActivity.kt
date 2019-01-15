package ru.jjba.jr2.presentation.ui.splash

import android.content.Intent
import androidx.lifecycle.Observer
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseActivity
import ru.jjba.jr2.presentation.ui.main.MainActivity
import ru.jjba.jr2.presentation.viewmodel.splash.SplashViewModel

class SplashActivity : BaseActivity<SplashViewModel>() {
    override var viewModel = SplashViewModel()
    override val layoutRes = R.layout.fragment_splash

    override fun initContent() {
        viewModel.onSetupDb()
    }

    override fun observeData() {
        viewModel.observeIsAllowedToNavToMain().observe(this, Observer { isAllowedToNav ->
            if (isAllowedToNav) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}