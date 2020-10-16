package info.firozansari.architecture_component.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.firozansari.architecture_component.Extension.gone
import info.firozansari.architecture_component.Extension.visible
import info.firozansari.architecture_component.models.Article
import info.firozansari.architecture_component.utils.DateUtils
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*


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
        itemView.blog_time_tv.text = DateUtils.covertDateToText(it.createdAt)

        it.user[0].avatar?.let { it1 ->
            Glide.with(itemView.context).load(it1)
                .into(itemView.blog_user_image)
        }
        if(it.media.isNotEmpty()){
            itemView.blog_title.text = it.media[0].title
            itemView.blog_url.text = it.media[0].url
            Glide.with(itemView.context).load(it.media[0].image)
                .into(itemView.blog_image)
            itemView.blog_image.visible()
            itemView.blog_title.visible()
            itemView.blog_url.visible()
        } else {
            itemView.blog_image.gone()
            itemView.blog_title.gone()
            itemView.blog_url.gone()
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
