package info.firozansari.architecture_component.ui.adapter

import android.R
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import info.firozansari.architecture_component.models.Article
import kotlinx.android.synthetic.main.item_article.view.*


/**
 * Created by Firoz Ansari on 15/10/2020.
 */
class ArticleViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

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
        itemView.blog_user_name_tv.text = it.user[0].name
        itemView.blog_user_des_tv.text = it.user[0].designation
        it.user[0].avatar?.let { it1 ->
            Glide.with(itemView.context).load(it1)
                .into(itemView.blog_user_image)

        }
////        itemView.recipe_title.text = it.title
//        itemView.recipe_ingredients.text = it.ingredients
    }

    private fun setListeners(
        listener: ArticlesAdapter.OnClickListener,
        article: Article
    ) {
        // TODO need implementing  onClicklistener
    }


}
