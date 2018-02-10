package com.fed.pitarestesttask.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.POJO.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * created by Fedor SURIN on 10.02.2018.
 */
class RecyclerAdapter(private var context: Context, private var articles: List<Result>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: RecyclerHolder?, position: Int) {
        holder?.bind(articles[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return RecyclerHolder(v)
    }


    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Result) {
            Picasso.with(context)
                    .load(article.multimedia.src)
                    .placeholder(context.getDrawable(R.drawable.android_pirate))
                    .into(itemView.preview_imageView)

            itemView.title_text_view.text = article.displayTitle
            itemView.date_text_view.text = article.publicationDate
            itemView.description_text_view.text = article.summaryShort
            itemView.read_more_text_view.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(article.link.url)
                Log.i("URL", article.link.url)
                context.startActivity(intent)
            }
        }
    }
}