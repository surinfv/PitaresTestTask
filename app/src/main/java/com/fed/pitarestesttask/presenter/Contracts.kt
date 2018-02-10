package com.fed.pitarestesttask.presenter

/**
 * created by Fedor SURIN on 10.02.2018.
 */
interface FragmentListInterface {

}

interface PresenterInterface {
    fun onFragmentLoaded()
    fun attachView(fragment: FragmentListInterface)
    fun detachView()
}