package com.fed.pitarestesttask.view

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.POJO.Result
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
    private var adapter: RecyclerAdapter? = null

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
        presenter.onFragmentLoaded()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }

    override fun updateAdapter(articles: List<Result>) {
//        if (adapter == null) {
        adapter = RecyclerAdapter(context, articles)
        recycler_id.adapter = adapter
//        } else {
//            adapter.setArticles(articles)
//            adapter.notifyDataSetChanged()
//        }
    }

    override fun showEmptyListDialog() {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.apply {
            setTitle("wrong answer from server")
            setMessage("had empty list")
            setButton(AlertDialog.BUTTON_POSITIVE, "try again", { _, _ ->
                presenter.onTryAgainButtonClicked()
            })
        }.show()
    }

    override fun hideProgressBar() {
        recycler_id.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}