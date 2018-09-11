package ru.jjba.jr2.presentation.ui.word.details

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_word_details.*
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsPresenter
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsView
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.ui.interp.InterpAdapter
import java.util.*

class WordDetailsFragment : BaseFragment(), WordDetailsView, TextToSpeech.OnInitListener {

    override val layoutRes: Int = R.layout.fragment_word_details
    override val titleDefault: String
        get() = getString(R.string.word_details_title)

    @InjectPresenter
    lateinit var presenter: WordDetailsPresenter

    private val wordId: Long?
        get() = arguments?.getLong(WORD_ID)
    private val interpAdapter = InterpAdapter()

    private lateinit var textToSpeech: TextToSpeech

    @ProvidePresenter
    fun provideWordDetailsPresenter(): WordDetailsPresenter =
            WordDetailsPresenter(wordId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isBnMainShown(false)

        textToSpeech = TextToSpeech(context, this)

        initContent()
    }

    // TODO : Refactor textToSpeech code to an appropriate format
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.JAPANESE)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                tvWordDetailsJp.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut(view: TextView) {
        val text = view.text.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }
    }

    private fun initContent() {
        rvInterp.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(context)
            it.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            it.adapter = interpAdapter
        }

        tvWordDetailsJp.setOnClickListener { speakOut(it as TextView) }
        tvWorddetailsFurigana.setOnClickListener { speakOut(it as TextView) }
    }

    override fun showWord(word: Word) {
        tvWordDetailsJp.text = word.wordJp
        tvWorddetailsFurigana.text = word.wordFurigana
        tvJlptLevel.text = "N${word.jlptLevel}"

        setTitle(word.wordJp)
    }

    override fun showInterps(interps: List<Interp>) {
        interpAdapter.interpList = interps
    }

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: Long?): WordDetailsFragment =
                WordDetailsFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}