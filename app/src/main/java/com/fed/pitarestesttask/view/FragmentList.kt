package com.fed.pitarestesttask.view

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.Element
import com.fed.pitarestesttask.model.POJO.Response
import com.fed.pitarestesttask.network.RetroClient
import kotlinx.android.synthetic.main.fragment_elements_list.*
import retrofit2.Call
import retrofit2.Callback

/**
 * created by Fedor SURIN on 10.02.2018.
 */
class FragmentList : Fragment() {
    private val TAG = "FragmentList"

    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_elements_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_id.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerAdapter(context, getElements())
        recycler_id.adapter = adapter
        doRequest()
    }

    private fun getElements(): ArrayList<Element> {
        val list = ArrayList<Element>()
        for (n in 1..200) list.add(Element("element $n"))
        return list
    }

    fun doRequest() {
        val api = RetroClient.apiService
        val call: Call<Response> = api.requestForArticles()
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {

                Log.i(TAG, "OK")
            }

            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                Log.i(TAG, "retrofit onFailure: " + t.toString())
            }
        })
    }
}