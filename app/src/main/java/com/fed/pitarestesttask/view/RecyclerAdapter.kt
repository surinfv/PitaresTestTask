package com.fed.pitarestesttask.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.Element
import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * created by Fedor SURIN on 10.02.2018.
 */
class RecyclerAdapter(private var context: Context, private var elementsList: ArrayList<Element>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    override fun getItemCount(): Int = elementsList.size

    override fun onBindViewHolder(holder: RecyclerHolder?, position: Int) {
        holder?.bind(elementsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.fragment_item, parent, false)
        return RecyclerHolder(v)
    }


    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(element: Element) {
            itemView.title_text_view.text = element.title
        }
    }
}