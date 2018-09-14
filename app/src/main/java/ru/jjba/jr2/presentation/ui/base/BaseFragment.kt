package ru.jjba.jr2.presentation.ui.base

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.design.widget.Snackbar
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.App
import ru.jjba.jr2.R
import ru.jjba.jr2.utils.BottomNavigationItem
import ru.jjba.jr2.utils.inflate
import ru.jjba.jr2.utils.isVisible

abstract class BaseFragment : MvpAppCompatFragment() {

    private val textToSpeech: TextToSpeech = App.instance.tts

    abstract val layoutRes: Int
    abstract val titleDefault: String

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.title = titleDefault
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId) {
        android.R.id.home -> requireActivity().onBackPressed().let { true }
        else -> super.onOptionsItemSelected(item)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isBnMainShown()
    }

    fun isBnMainShown(isShown: Boolean = true) {
        requireActivity().navigation.isVisible = isShown
    }

    fun setTitle(title: String) {
        activity?.title = title
    }

    fun speakOut(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }
}