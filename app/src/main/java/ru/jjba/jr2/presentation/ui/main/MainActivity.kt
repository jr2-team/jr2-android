package ru.jjba.jr2.presentation.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word

class MainActivity(
        val wordDbRepository: WordDbRepository = WordDbRepository(),
        val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository()
) : AppCompatActivity() {

    private val wordAdapter = WordAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initContent()
    }

    // TODO : Добавить слой presentation и interact
    private fun initContent() {
        wordDbRepository.insert(
                listOf(
                        Word("1", "家", "いえ", 5),
                        Word("2", "今日は", "こんにち", 5)
                )
        ).subscribeBy { }

        interpretationDbRepository.insert(Interpretation("1", "house; residence; dwelling", "noun", "1")).subscribeBy {  }
        interpretationDbRepository.insert(Interpretation("2", "family; household", "noun", "1")).subscribeBy {  }
        interpretationDbRepository.insert(Interpretation("3", "hello; good day; good afternoon", "", "2")).subscribeBy {  }

        wordDbRepository.getAll().first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            wordAdapter.wordList = it
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )


        rvWord.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
            it.adapter = wordAdapter
        }
    }
}
