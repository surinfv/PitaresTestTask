package com.fed.pitarestesttask.presenter

import android.util.Log
import com.fed.pitarestesttask.model.ApiResponse
import com.fed.pitarestesttask.model.ResultsItem
import com.fed.pitarestesttask.network.RetroClient
import retrofit2.Call
import retrofit2.Callback


class Presenter : PresenterInterface {
    private val TAG = Presenter::class.java.simpleName
    private var isLoading = false
    private var searchString = ""
    private var page = 0
    private var fragment: FragmentListInterface? = null
    private var allArticles: MutableList<ResultsItem> = ArrayList()

    override fun onFragmentLoaded() {
        doRequest()
    }

    override fun onWrongResponseDialogButtonClicked() {
        doRequest()
    }

    override fun onEmptyListDialogButtonClicked() {
        searchString = ""
        doRequest()
    }

    override fun onSearchButtonClicked() {
        page = 0
        doRequest()
    }

    override fun onSwipeRefresh() {
        page = 0
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
            this.searchString = ""
        }

    }

    override fun attachView(fragment: FragmentListInterface) {
        this.fragment = fragment
    }

    override fun detachView() {
        fragment = null
    }

    private fun doRequest() {
        if (!isLoading) {
            onLoading(true)
            Log.i(TAG, "page -> $page ; query -> $searchString")
            val api = RetroClient.apiService
            val call: Call<ApiResponse> = api.requestForArticles(searchString, page * 20)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>?, response: retrofit2.Response<ApiResponse>?) {
                    val newArticles = response?.body()?.results
                    if (newArticles != null && newArticles.isNotEmpty()) {
                        if (page == 0) allArticles.clear()
                        allArticles.addAll(newArticles)
                        fragment?.updateAdapter(allArticles)

                    } else {
                        fragment?.showEmptyListDialog()
                    }
                    onLoading(false)
                    Log.i(TAG, "OK")
                }

                override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                    Log.i(TAG, "retrofit onFailure: " + t.toString())
                    onLoading(false)
                    fragment?.showWrongResponseDialog()
                }
            })
        }
    }

    private fun onLoading(isItStartLoading: Boolean) {
        if (isItStartLoading) {
            fragment?.showProgressBar()
            isLoading = true
        } else {
            fragment?.hideProgressBar()
            isLoading = false
        }
    }
}
