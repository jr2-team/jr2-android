import com.arellomobile.mvp.presenter.InjectPresenter
import ru.jjba.jr2.presentation.presenters.words.list.WordListPresenter
import ru.jjba.jr2.presentation.presenters.words.list.WordListView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class WordListFragment : BaseFragment(), WordListView {

    override val layoutRes: Int = TODO("add layout res")
    override val titleDefault: String
        get() = TODO("add title default")

    //todo complete fragment
    @InjectPresenter
    lateinit var presenter: WordListPresenter


}


