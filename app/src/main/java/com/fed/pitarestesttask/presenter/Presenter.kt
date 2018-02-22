package com.fed.pitarestesttask.presenter

import android.util.Log
import com.fed.pitarestesttask.model.POJO.Response
import com.fed.pitarestesttask.network.RetroClient
import retrofit2.Call
import retrofit2.Callback

/**
 * created by Fedor SURIN on 10.02.2018.
 */
class Presenter : PresenterInterface {

    private val TAG = "Presenter"
    private var searchString: String? = null
    private var fragment: FragmentListInterface? = null

    override fun onFragmentLoaded() {
        doRequest()
    }

    override fun onTryAgainButtonClicked() {
        doRequest()
    }

    override fun onSearchButtonClicked() {
        doRequest()
    }

    override fun onSwipeRefresh() {
        doRequest()
    }

    override fun setStringForSearch(string: String?) {
        if (string != null && string.isNotEmpty()) {
            this.searchString = string
        } else {
            this.searchString = null
        }

    }

    override fun attachView(fragment: FragmentListInterface) {
        this.fragment = fragment
    }

    override fun detachView() {
        fragment = null
    }

    private fun doRequest() {
        fragment?.showProgressBar()
        val api = RetroClient.apiService
        val call: Call<Response> = api.requestForArticles(searchString)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                val articles = response?.body()?.results
                if (articles != null) {
                    fragment?.updateAdapter(articles)
                    fragment?.hideProgressBar()
                } else {
                    fragment?.showEmptyListDialog()
                }
                Log.i(TAG, "OK")
            }

            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                Log.i(TAG, "retrofit onFailure: " + t.toString())
            }
        })
    }
}
