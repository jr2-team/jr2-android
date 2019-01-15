package ru.jjba.jr2.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.main.MainActivity
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    val d = CompositeDisposable()

    // TODO: Добавить бэкграунд (https://plus.google.com/+AndroidDevelopers/posts/Z1Wwainpjhd)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        emptyList<Unit>().toObservable()
                .delay(2, TimeUnit.SECONDS)
                .doOnComplete {
                    navToMainScreen()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy()
                .addTo(d)
    }

    private fun navToMainScreen() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}