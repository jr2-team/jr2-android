package ru.jjba.jr2.presentation.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.jetbrains.anko.support.v4.act

abstract class BaseDialog : DialogFragment() {
    internal lateinit var inflatedView: View
    internal abstract val layoutRes: Int

    abstract fun buildDialog(): Dialog
    abstract fun initContent()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        inflatedView = act.layoutInflater.inflate(layoutRes, null)
                ?: throw IllegalStateException()
        return buildDialog()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        initContent()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}