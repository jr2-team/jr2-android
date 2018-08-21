package ru.jjba.jr2.presentation.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.jjba.jr2.utils.inflate

abstract class BaseFragment : MvpAppCompatFragment() {

    abstract val layoutRes: Int
    abstract val titleDefault: String

    /**
     * Устанавливает в заголовок AppBar строку [titleDefault]
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.title = titleDefault
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? =
            container?.inflate(layoutRes).also {
                setHasOptionsMenu(true)
            }

    private var progressDialog: ProgressDialog? = null

    fun setSubtitle(subtitle: String?) {
        (activity as AppCompatActivity).supportActionBar?.subtitle = subtitle
    }

    open fun showMessage(msg: String) {
        val view = view ?: return
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    open fun showMessage(resMsg: Int) {
        val view = view ?: return
        Snackbar.make(view, resMsg, Snackbar.LENGTH_SHORT).show()
    }

    open fun setLoaderVisibility(isVisible: Boolean) {
        if (isVisible && progressDialog?.isShowing != true) {
            progressDialog = ProgressDialog.show(this.context, "Отправка данных", "Пожалуйста, подождите", true, false)
        } else if (!isVisible && progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }
}