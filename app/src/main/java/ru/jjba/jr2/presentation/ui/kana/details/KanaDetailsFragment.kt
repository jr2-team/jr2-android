package ru.jjba.jr2.presentation.ui.kana.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.kana.details.KanaDetailsViewModel

class KanaDetailsFragment : BaseFragment() {
    override var viewModel: ViewModel = KanaDetailsViewModel()
    override val layoutRes: Int = R.layout.fragment_kana_detail
    override val titleDefault: String
        get() = ""

    private val kanaId: String
        get() = arguments?.getString(KanaDetailsFragment.KANA_ID) ?: "0"

    private var kanaMode: Boolean = false
    private lateinit var kana: Kana

    override fun initContent() {
        /*showBottomNavigationView(false)
        secondKana.setOnClickListener {
            //todo переделать листенер доп каны в деталях каны
            if(kanaMode) {
                mainKana.text = kana.hiragana
                secondKana.text = kana.katakana
                secondKanaTitle.text = "Katakana"
                requireActivity().title = kana.hiragana
            } else {
                mainKana.text = kana.katakana
                secondKana.text = kana.hiragana
                secondKanaTitle.text = "Hiragana"
                requireActivity().title = kana.katakana
            }
            kanaMode = !kanaMode
        }*/
    }

    override fun observeData() {
        /*override fun showKana(kana: Kana) {
        this.kana = kana
        mainKana.text = kana.hiragana
        secondKana.text = kana.katakana
        setTitle(kana.hiragana)
        }*/
    }

    companion object {
        const val KANA_ID = "kana id"

        fun newInstance(kanaId: String?): KanaDetailsFragment =
                KanaDetailsFragment().also {
                    it.arguments = bundleOf(KANA_ID to kanaId)
                }
    }
}