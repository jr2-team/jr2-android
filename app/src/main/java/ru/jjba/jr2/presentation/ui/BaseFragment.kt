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
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.App
import ru.jjba.jr2.utils.InstanceStateProvider
import ru.jjba.jr2.utils.createFactory
import ru.jjba.jr2.utils.inflate
import ru.jjba.jr2.utils.isVisible

abstract class BaseFragment<VT : ViewModel> : Fragment(), LifecycleObserver {
    abstract var viewModel: VT
    abstract val layoutRes: Int
    abstract val titleDefault: String

    private val textToSpeech: TextToSpeech = App.instance.tts
    private val savable = Bundle()

    protected fun <T> instanceState() =
            InstanceStateProvider.Nullable<T>(savable)
    protected fun <T> instanceState(defaultValue: T) =
            InstanceStateProvider.NotNull(savable, defaultValue)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun initContent() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun observeData() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun saveInstanceState() {}

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
        act.bottomNavigation.isVisible = isShown
    }

    fun speakOut(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(savedInstanceState != null) {
            savable.putAll(savedInstanceState.getBundle("_state"))
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            state: Bundle?
    ): View? = container?.inflate(layoutRes).also {
        setHasOptionsMenu(true)
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle("_state", savable)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        lifecycle.removeObserver(this)
        super.onPause()
    }
}