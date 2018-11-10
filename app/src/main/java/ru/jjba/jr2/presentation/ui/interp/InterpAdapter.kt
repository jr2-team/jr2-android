package ru.jjba.jr2.presentation.ui.interp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_word_detail_item_interp.view.*
import ru.jjba.jr2.R
//import ru.jjba.jr2.data.repository.ExampleDbRepository
import ru.jjba.jr2.domain.entity.Interp
import kotlin.properties.Delegates

class InterpAdapter(
        //val exampleDbRepository: ExampleDbRepository = ExampleDbRepository()
) : RecyclerView.Adapter<InterpAdapter.ViewHolder>() {
    var interpList: List<Interp> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_word_detail_item_interp, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = interpList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(interpList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(interpretation: Interp) = with(itemView) {
            tvInterp.text = interpretation.interp
            tvPos.text = interpretation.pos

            setOnClickListener {
                /*exampleDbRepository.getExampleByInterpretationId(interp.id)
                        .first(kotlin.collections.emptyList())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = {
                                    it.forEach {
                                        Snackbar.make(this, it.toString(), Snackbar.LENGTH_LONG).show()
                                    }
                                }
                        )*/
            }
        }
    }
}