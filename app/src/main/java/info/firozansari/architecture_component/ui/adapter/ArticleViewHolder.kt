package info.firozansari.architecture_component.ui.adapter

import android.R
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import info.firozansari.architecture_component.Extension.gone
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
        itemView.blog_like_count_tv.text = """${getFormattedNumber(it.likes)} Likes"""
        itemView.blog_comment_count_tv.text = """${getFormattedNumber(it.comments)} Comments"""
        itemView.blog_content.text = it.content

        it.user[0].avatar?.let { it1 ->
            Glide.with(itemView.context).load(it1)
                .into(itemView.blog_user_image)
        }
        if(it.media.isNotEmpty()){
            Glide.with(itemView.context).load(it.media[0].image)
                .into(itemView.blog_image)
        } else {
            itemView.blog_image.gone()
        }

    }

    private fun getFormattedNumber(number: Int): String {
        var numberString = ""
        numberString = when {
            Math.abs(number / 1000) > 1 -> {
                (number / 1000).toString() + "k";
            }
            else -> {
                number.toString();
            }
        }
        return numberString
    }

    private fun setListeners(
        listener: ArticlesAdapter.OnClickListener,
        article: Article
    ) {
        // TODO need implementing  onClicklistener
    }


}
