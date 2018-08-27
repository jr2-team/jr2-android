package ru.jjba.jr2.presentation.ui.base

import android.os.Bundle
import android.support.design.widget.Snackbar
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

    open fun showMessage(msg: String) {
        val view = view ?: return
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    open fun showMessage(resMsg: Int) {
        val view = view ?: return
        Snackbar.make(view, resMsg, Snackbar.LENGTH_SHORT).show()
    }
}