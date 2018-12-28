package ru.jjba.jr2.presentation.ui

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.App
import ru.jjba.jr2.R
import ru.jjba.jr2.utils.createFactory
import ru.jjba.jr2.utils.inflate
import ru.jjba.jr2.utils.isVisible

abstract class BaseFragment<VT : ViewModel> : Fragment(), LifecycleObserver {
    abstract val layoutRes: Int
    abstract val titleDefault: String
    abstract var viewModel: VT

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
        act.toolbar.title = title
    }

    fun showToolbar(isShown: Boolean = true) {
        act.toolbar.isVisible = isShown
    }

    fun showBottomNavigation(isShown: Boolean = true) {
        act.bottomNavigationView.isVisible = isShown
    }

    fun speakOut(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> act.onBackPressed().let { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? {
        val view = container?.inflate(layoutRes)
        // TODO: Разобраться с toolbar
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(titleDefault)
        showToolbar()
        showBottomNavigation()

        viewModel = ViewModelProviders
                .of(act, viewModel.createFactory())
                .get(viewModel::class.java)

        lifecycle.addObserver(this)
    }
}