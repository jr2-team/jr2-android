package ru.jjba.jr2.presentation.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.example.ExampleDbRepository
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
import ru.jjba.jr2.data.repository.kanji.KanjiDbRepository
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.*
import ru.jjba.jr2.domain.interactor.WordInteractor

class MainActivity(
        val kanjiDbRepository: KanjiDbRepository = KanjiDbRepository()
) : AppCompatActivity() {

    private val wordAdapter = WordAdapter()

    // TODO : Добавить слой presentation и interact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordTest()
        kanjiTest()
    }

    private fun wordTest() {

        WordInteractor().insertWord(
                Word("1", "家", "いえ", 5),
                mapOf(
                        Pair(
                                Interpretation("1", "house; residence; dwelling", "noun", "1"),
                                listOf(Example("1", "木立の間に家が見える", "こ|だち|の|あいだ|に|いえ|が|み|える", "I see a house among the trees.", "1"))
                        ),
                        Pair(
                                Interpretation("2", "family; household", "noun", "1"),
                                emptyList()
                        )
                )
        ).subscribeBy()

        WordInteractor().insertWord(
                Word("2", "今日は", "こんにち", 5),
                mapOf(
                        Pair(
                                Interpretation("3", "hello; good day; good afternoon", "", "2"),
                                emptyList()
                        )
                )
        ).subscribeBy()

        WordInteractor().getAllWords()
                .first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            val list = it as MutableList<Word>
                            list.add(Word("0", "", "", 0))
                            wordAdapter.wordList = list
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

    private fun kanjiTest() {
        kanjiDbRepository.insert(
                listOf(
                        Kanji("1", "国"),
                        Kanji("2", "王"),
                        Kanji("3", "丶"),
                        Kanji("4", "囗"),
                        Kanji("5", "大"),
                        Kanji("6", "火")
                )
        ).subscribeBy { }

        kanjiDbRepository.insertKanjiPart(
                listOf(
                        KanjiPart("1", "1", "2", 1),
                        KanjiPart("2", "1", "3", 2),
                        KanjiPart("3", "1", "4", 3)
                )
        ).subscribeBy { }

        kanjiDbRepository.getKanjiPartsByKanjiId("1").first(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            val l = it
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
    }
}
