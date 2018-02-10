package com.fed.pitarestesttask.view

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.Element
import com.fed.pitarestesttask.presenter.FragmentListInterface
import com.fed.pitarestesttask.presenter.Presenter
import com.fed.pitarestesttask.presenter.PresenterInterface
import kotlinx.android.synthetic.main.fragment_elements_list.*

/**
 * created by Fedor SURIN on 10.02.2018.
 */
class FragmentList : Fragment(), FragmentListInterface {
    private val TAG = "FragmentList"

    private lateinit var presenter: PresenterInterface
    private lateinit var adapter: RecyclerAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = Presenter()
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_elements_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_id.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerAdapter(context, getElements())
        recycler_id.adapter = adapter
        presenter.onFragmentLoaded()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }



    //fixme - placeholder
    private fun getElements(): ArrayList<Element> {
        val list = ArrayList<Element>()
        for (n in 1..200) list.add(Element("element $n"))
        return list
    }


}