package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup.LayoutParams.FILL_PARENT
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import org.jetbrains.anko.act
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import ru.jjba.jr2.App
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.ui.util.BreadCrumble
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel
import kotlin.random.Random

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
        get() = ""

    var save: String by instanceState(String())

    private val args by navArgs<WordDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        viewModel.setArgs(args.wordId)

        btnNavTo.onClick {
            val d = WordDetailFragmentDirections.actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
            findNavController().navigate(d)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            App.instance.detailCrumbleController.crumbles.clear()
            findNavController().popBackStack(R.id.wordListFragment, false)
            true
        }
        else -> false
    }

    override fun initContent() {
        //setTitle(App.instance.detailCrumbleController.getComposedTitle())
        viewModel.fetchData()
    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            tvWordJp.text = word.value
            //setTitle(word.value)
            //setTitle(App.instance.detailCrumbleController.getComposedTitle())
            App.instance.detailCrumbleController.crumbles.update(BreadCrumble(word.value))
        })

        tvCustom.onClick {
            toast(App.instance.detailCrumbleController.getComposedTitle())
        }
    }

    fun <T> MutableList<T>.update(item: T) {
        if (!this.contains(item)) {
            this.add(item)
        }
    }
}