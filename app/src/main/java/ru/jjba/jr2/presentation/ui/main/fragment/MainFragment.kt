package ru.jjba.jr2.presentation.ui.main.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_word_detail_header.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment

class MainFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_main
    override val titleDefault: String
        get() = getString(R.string.main_title)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}