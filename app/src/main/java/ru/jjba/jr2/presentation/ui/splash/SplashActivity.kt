package ru.jjba.jr2.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.main.MainActivity
import ru.jjba.jr2.presentation.viewmodel.splash.SplashViewModel

class SplashActivity : AppCompatActivity(), LifecycleObserver {
    lateinit var viewModel: SplashViewModel

    // TODO: Добавить бэкграунд (https://plus.google.com/+AndroidDevelopers/posts/Z1Wwainpjhd)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.onSetupDb()

        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun observeData() {
        viewModel.observeIsAllowedNavToMain().observe(this, Observer { isAllowedToNav ->
            if (isAllowedToNav) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}