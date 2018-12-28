package ru.jjba.jr2.presentation.ui

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.App
import ru.jjba.jr2.utils.createFactory
import ru.jjba.jr2.utils.inflate
import ru.jjba.jr2.utils.isVisible

abstract class BaseFragment : Fragment(), LifecycleObserver {
    abstract val layoutRes: Int
    abstract val titleDefault: String
    abstract var viewModel: ViewModel

    private val textToSpeech: TextToSpeech = App.instance.tts

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected abstract fun initContent()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected abstract fun observeData()

    fun showMessage(msg: String) {
        val view = view ?: return
        view.snackbar(msg)
    }

    fun showMessage(resMsg: Int) {
        val view = view ?: return
        view.snackbar(resMsg)
    }

    fun setTitle(title: String) {
        act.toolbar.title = titleDefault
    }

    fun showBottomNavigationView(isShown: Boolean = true) {
        act.bottomNavigationView.isVisible = isShown
    }

    fun speakOut(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> requireActivity().onBackPressed().let { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? {
        return container?.inflate(layoutRes).also {
            setHasOptionsMenu(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(titleDefault)
        showBottomNavigationView()

        viewModel = ViewModelProviders
                .of(act, viewModel.createFactory())
                .get(viewModel::class.java)

        lifecycle.addObserver(this)
    }
}