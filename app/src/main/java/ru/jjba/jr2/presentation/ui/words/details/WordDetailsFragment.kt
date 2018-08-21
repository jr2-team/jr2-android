package ru.jjba.jr2.presentation.ui.words.details

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.jjba.jr2.presentation.presenters.words.details.WordDetailsPresenter
import ru.jjba.jr2.presentation.presenters.words.details.WordDetailsView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class WordDetailsFragment : BaseFragment(), WordDetailsView {

    override val layoutRes: Int = TODO("add layout res")
    override val titleDefault: String
        get() = TODO("add title default")

    //todo complete fragment
    @InjectPresenter
    lateinit var presenter: WordDetailsPresenter


}