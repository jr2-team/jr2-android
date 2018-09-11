package ru.jjba.jr2.presentation.ui.main.fragment

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentPresenter
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class MainFragment : BaseFragment(), MainFragmentView {

    override val layoutRes: Int = R.layout.fragment_main
    override val titleDefault: String
        get() = getString(R.string.main_title)

    @InjectPresenter
    lateinit var presenter: MainFragmentPresenter
}