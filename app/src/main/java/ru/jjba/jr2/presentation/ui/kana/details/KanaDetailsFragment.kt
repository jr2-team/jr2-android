package ru.jjba.jr2.presentation.ui.kana.details

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_kana_details.*
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.presentation.presenters.kana.details.KanaDetailsPresenter
import ru.jjba.jr2.presentation.presenters.kana.details.KanaDetailsView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class KanaDetailsFragment: BaseFragment(), KanaDetailsView {
    override val layoutRes: Int = R.layout.fragment_kana_details
    override val titleDefault: String
        get() = ""

    private val kanaId: String
        get() = arguments?.getString(KanaDetailsFragment.KANA_ID) ?: "0"

    private var kanaMode: Boolean = false
    private lateinit var kana: Kana

    @InjectPresenter
    lateinit var presenter: KanaDetailsPresenter

    @ProvidePresenter
    fun provideKanaDetailsPresenter(): KanaDetailsPresenter =
            KanaDetailsPresenter(kanaId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
        isBnMainShown(false)
        kanaSVGView.loadSvg(resources.assets.open("kanjivg/04e0b.svg"))
        secondKana.setOnClickListener {
            //todo переделать листенер доп каны в деталях каны
            if(kanaMode) {
                secondKana.text = kana.katakana
                secondKanaTitle.text = "Katakana"
                requireActivity().title = kana.hiragana
            } else {
                secondKana.text = kana.hiragana
                secondKanaTitle.text = "Hiragana"
                requireActivity().title = kana.katakana
            }
            kanaMode = !kanaMode
        }

    }

    override fun showKana(kana: Kana) {
        this.kana = kana
        secondKana.text = kana.katakana
        setTitle(kana.hiragana)
    }

    companion object {
        const val KANA_ID = "kana id"

        fun newInstance(kanaId: String?): KanaDetailsFragment =
                KanaDetailsFragment().also {
                    it.arguments = bundleOf(KANA_ID to kanaId)
                }
    }
}