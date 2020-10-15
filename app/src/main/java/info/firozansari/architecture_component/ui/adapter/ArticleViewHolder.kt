package info.firozansari.architecture_component.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.architecture_component.models.Article

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class ArticleViewHolder (parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        article: Article?,
        listener: ArticlesAdapter.OnClickListener
    ) {
        article?.let {
            setupViews(it, itemView)
            setListeners(listener, article)
        }
    }

    fun setupViews(it: Article, itemView: View) {

    }

    private fun setListeners(
        listener: ArticlesAdapter.OnClickListener,
        article: Article
    ) {
        // TODO need implementing  onClicklistener
    }


}
