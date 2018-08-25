package ru.jjba.jr2.presentation.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentPresenter
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentView
import ru.jjba.jr2.presentation.presenters.word.list.WordListPresenter
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class MainFragment : BaseFragment(), MainFragmentView {

    override val layoutRes: Int = R.layout.fragment_main
    override val titleDefault: String
        get() = getString(R.string.main_title)

    @InjectPresenter
    lateinit var presenter: MainFragmentPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
        btnToWordList.setOnClickListener {
            presenter.onWordListClicked()
        }
    }
}