package ru.jjba.jr2.presentation.viewmodel.vocab.kanji

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.repository.ComponentDbRepository
import ru.jjba.jr2.data.repository.KanaDbRepository
import ru.jjba.jr2.data.repository.KanjiDbRepository
import ru.jjba.jr2.domain.entity.Component
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.domain.entity.Kanji

class KanjiGroupViewModel(
        private val app: App = App.instance
) : ViewModel() {
    val moshi = Moshi.Builder().build()

    val kanjiRepository = KanjiDbRepository()
    val componentRepository = ComponentDbRepository()
    val kanaRepository = KanaDbRepository()

    lateinit var kanji: LiveData<List<Kanji>>
    lateinit var comp: LiveData<List<Component>>
    lateinit var kanjicomp: LiveData<List<Kanji>>
    lateinit var kana: LiveData<List<Kana>>

    fun test() {
        /*val d = insertKanji()
                .andThen(insertComponents())
                .andThen(insertKana())
                .subscribeBy()*/

        kanji = kanjiRepository.getAll()
        comp = componentRepository.getAll()
        kanjicomp = kanjiRepository.getKanjiComponents(1)
        kana = kanaRepository.getAll()
    }

    fun insertKanji(): Completable {
        val kanjiAdapter: JsonAdapter<List<Kanji>> =  moshi.adapter(
                Types.newParameterizedType(List::class.java, Kanji::class.java)
        )
        val kanjis = kanjiAdapter.fromJson(app.readAsset("kanji.json"))

        return kanjiRepository.insertMany(kanjis!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertComponents(): Completable {
        val componentAdapter: JsonAdapter<List<Component>> =  moshi.adapter(
                Types.newParameterizedType(List::class.java, Component::class.java)
        )
        val kanjiComponents = componentAdapter.fromJson(app.readAsset("kanjiComponent.json"))

        return componentRepository.insertMany(kanjiComponents!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertKana(): Completable {
        val componentAdapter: JsonAdapter<List<Kana>> =  moshi.adapter(
                Types.newParameterizedType(List::class.java, Kana::class.java)
        )
        val piecesOfKana = componentAdapter.fromJson(app.readAsset("kana.json"))

        return kanaRepository.insertMany(piecesOfKana!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}