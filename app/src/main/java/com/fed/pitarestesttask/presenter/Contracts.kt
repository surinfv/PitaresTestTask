package com.fed.pitarestesttask.presenter

import com.fed.pitarestesttask.model.ResultsItem


interface FragmentListInterface {
    fun updateAdapter(articles: List<ResultsItem>)
    fun showWrongResponseDialog()
    fun showProgressBar()
    fun hideProgressBar()
    fun setLoadingFlag(isLoading: Boolean)
}

interface PresenterInterface {
    fun onFragmentLoaded()
    fun onWrongResponseDialogButtonClicked()
    fun onSearchButtonClicked()
    fun onSwipeRefresh()
    fun attachView(fragment: FragmentListInterface)
    fun detachView()
    fun setStringForSearch(string: String?)
    fun lastItemShown()
}