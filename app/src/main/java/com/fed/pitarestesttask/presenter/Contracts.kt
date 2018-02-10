package com.fed.pitarestesttask.presenter

import com.fed.pitarestesttask.model.POJO.Result

/**
 * created by Fedor SURIN on 10.02.2018.
 */
interface FragmentListInterface {
    fun updateAdapter(articles: List<Result>)
    fun showEmptyListDialog()
    fun hideProgressBar()
}

interface PresenterInterface {
    fun onFragmentLoaded()
    fun onTryAgainButtonClicked()
    fun attachView(fragment: FragmentListInterface)
    fun detachView()
}