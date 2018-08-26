package ru.jjba.jr2.presentation.ui.base

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.jjba.jr2.utils.inflate

abstract class BaseFragment : MvpAppCompatFragment() {

    abstract val layoutRes: Int
    abstract val titleDefault: String

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.title = titleDefault
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? =
            container?.inflate(layoutRes).also {
                setHasOptionsMenu(true)
            }
}