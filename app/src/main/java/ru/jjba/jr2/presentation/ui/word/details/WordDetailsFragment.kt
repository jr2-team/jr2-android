package ru.jjba.jr2.presentation.ui.word.details

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsPresenter
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class WordDetailsFragment : BaseFragment(), WordDetailsView {

    override val layoutRes: Int = R.layout.fragment_word_details
    override val titleDefault: String
        get() = getString(R.string.word_details_title)

    @InjectPresenter
    lateinit var presenter: WordDetailsPresenter
}