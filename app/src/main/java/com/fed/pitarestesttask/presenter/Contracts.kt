package com.fed.pitarestesttask.presenter

import com.fed.pitarestesttask.model.ResultsItem


interface FragmentListInterface {
    fun updateAdapter(articles: List<ResultsItem>)
    fun showEmptyListDialog()
    fun showProgressBar()
    fun hideProgressBar()
}

interface PresenterInterface {
    fun onFragmentLoaded()
    fun onTryAgainButtonClicked()
    fun onSearchButtonClicked()
    fun onSwipeRefresh()
    fun attachView(fragment: FragmentListInterface)
    fun detachView()
    fun setStringForSearch(string: String?)
}