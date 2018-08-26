package ru.jjba.jr2.presentation.ui.tests

import com.arellomobile.mvp.presenter.InjectPresenter
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.presenters.tests.TestPresenter
import ru.jjba.jr2.presentation.presenters.tests.TestView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class TestFragment : BaseFragment(), TestView {

    override val layoutRes: Int = R.layout.fragment_test
    override val titleDefault: String
        get() = getString(R.string.test_title)

    @InjectPresenter
    lateinit var presenter: TestPresenter
}