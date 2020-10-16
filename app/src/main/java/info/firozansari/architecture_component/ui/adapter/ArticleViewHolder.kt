package info.firozansari.architecture_component.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.firozansari.architecture_component.Extension.gone
import info.firozansari.architecture_component.Extension.visible
import info.firozansari.architecture_component.datasource.ArticleData
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
        article: ArticleData?
    ) {
        article?.let {
            setupViews(it, itemView)
        }
    }

    fun setupViews(it: ArticleData, itemView: View) {
        itemView.blog_user_name_tv.text = it.username
        itemView.blog_user_des_tv.text = it.designation
        itemView.blog_like_count_tv.text = """${getFormattedNumber(it.likes)} Likes"""
        itemView.blog_comment_count_tv.text = """${getFormattedNumber(it.comments)} Comments"""
        itemView.blog_content.text = it.blogcontent
        itemView.blog_time_tv.text = DateUtils.covertDateToText(it.created)

        Glide.with(itemView.context).load(it.userimage).into(itemView.blog_user_image)

        val blogImage = it.blogimage
        if(blogImage.isNullOrEmpty()){
            itemView.blog_image.gone()
        } else {
            Glide.with(itemView.context).load(blogImage)
                .into(itemView.blog_image)
            itemView.blog_image.visible()
        }

        val blogTitle = it.blogtitle
        if(blogTitle.isNullOrEmpty()) {
            itemView.blog_title.gone()
        } else {
            itemView.blog_title.text = blogTitle.toString()
            itemView.blog_title.visible()
        }

        val blogUrl = it.blogurl
        if(blogUrl.isNullOrEmpty()){
            itemView.blog_url.gone()
        } else {
            itemView.blog_url.text = blogUrl.toString()
            itemView.blog_url.visible()
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


}
