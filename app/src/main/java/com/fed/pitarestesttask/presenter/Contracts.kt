package com.fed.pitarestesttask.presenter

import com.fed.pitarestesttask.model.POJO.Result

/**
 * created by Fedor SURIN on 10.02.2018.
 */
interface FragmentListInterface {
    fun updateAdapter(articles: List<Result>)
    fun showEmptyListDialog()
}

interface PresenterInterface {
    fun onFragmentLoaded()
    fun attachView(fragment: FragmentListInterface)
    fun detachView()
}