package com.fed.pitarestesttask.presenter

import android.content.ContentValues.TAG
import android.util.Log
import com.fed.pitarestesttask.model.ApiResponse
import com.fed.pitarestesttask.model.ResultsItem
import com.fed.pitarestesttask.network.RetroClient
import retrofit2.Call
import retrofit2.Callback


class Presenter : PresenterInterface {

    private var searchString: String? = null
    private var page = 0
    private var fragment: FragmentListInterface? = null
    private var allArticles: MutableList<ResultsItem> = ArrayList()

    override fun onFragmentLoaded() {
        doRequest()
    }

    override fun onWrongResponseDialogButtonClicked() {
        doRequest()
    }

    override fun onSearchButtonClicked() {
        doRequest()
    }

    override fun onSwipeRefresh() {
        page = 0
        allArticles.clear()
        doRequest()
    }

    override fun lastItemShown() {
        page++
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
        onLoadingStart(true)
        val api = RetroClient.apiService
        val call: Call<ApiResponse> = api.requestForArticles(searchString, page * 20)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: retrofit2.Response<ApiResponse>?) {
                val newArticles = response?.body()?.results
                if (newArticles != null) {
                    allArticles.addAll(newArticles)
                    fragment?.updateAdapter(allArticles)

                } else {
                    fragment?.showWrongResponseDialog()
                }
                fragment?.hideProgressBar()
                fragment?.setLoadingFlag(false)
                Log.i(TAG, "OK")
            }

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                Log.i(TAG, "retrofit onFailure: " + t.toString())
                onLoadingStart(false)
                fragment?.showWrongResponseDialog()
            }
        })
    }

    private fun onLoadingStart(isItStartLoading: Boolean) {
        if (isItStartLoading) {
            fragment?.showProgressBar()
            fragment?.setLoadingFlag(true)
        } else {
            fragment?.hideProgressBar()
            fragment?.setLoadingFlag(false)
        }
    }
}
