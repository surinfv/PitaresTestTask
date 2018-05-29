package com.fed.pitarestesttask.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.ResultsItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class RecyclerAdapter(private var context: Context,
                      private var articles: List<ResultsItem>,
                      private val onReadMoreClickListener: (String) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    fun setArticles(newArticles: List<ResultsItem>) {
        articles = newArticles
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: RecyclerHolder?, position: Int) {
        holder?.bind(articles[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return RecyclerHolder(v)
    }

    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: ResultsItem) {
            if (article.multimedia != null) {
                Picasso.with(context)
                        .load(article.multimedia.src)
                        .placeholder(context.getDrawable(R.drawable.android_pirate))
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(itemView.preview_imageView)
                itemView.preview_imageView.visibility = View.VISIBLE
            } else {
                itemView.preview_imageView.visibility = View.GONE
            }

            itemView.title_text_view.text = article.displayTitle
            itemView.date_text_view.text = article.publicationDate
            itemView.description_text_view.text = article.summaryShort
            itemView.read_more_text_view.setOnClickListener { onReadMoreClickListener.invoke(article.link.url) }
            if (article.criticsPick == 1) itemView.critic_pick_star.visibility = View.VISIBLE
        }
    }
}