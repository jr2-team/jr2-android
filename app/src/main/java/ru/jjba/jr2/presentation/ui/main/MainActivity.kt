package ru.jjba.jr2.presentation.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Word

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordDbRepository = WordDbRepository()

        wordDbRepository.insert(Word("家", "いえ", 5)).subscribeBy {  }

        val listSingle = wordDbRepository.getAll().first(emptyList())
        listSingle
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                    onSuccess = {
                        val list = it
                        val s = ""
                    }
            )

    }
}
