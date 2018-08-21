package ru.jjba.jr2.presentation.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Word

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordDbRepository = WordDbRepository()

        wordDbRepository.insert(Word("家", "いえ", 5))
    }
}
