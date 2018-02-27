package com.fed.pitarestesttask.view

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.SearchView
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.ResultsItem
import com.fed.pitarestesttask.presenter.FragmentListInterface
import com.fed.pitarestesttask.presenter.Presenter
import com.fed.pitarestesttask.presenter.PresenterInterface
import kotlinx.android.synthetic.main.fragment_elements_list.*


class FragmentList : Fragment(), FragmentListInterface {
    private lateinit var presenter: PresenterInterface
    private var adapter: RecyclerAdapter? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = Presenter()
        presenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_elements_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_id.layoutManager = LinearLayoutManager(context)
        presenter.onFragmentLoaded()
        swipe_container.setOnRefreshListener { presenter.onSwipeRefresh() }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)

        val searchItem = menu?.findItem(R.id.search_item)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.setStringForSearch(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onSearchButtonClicked()
                return false
            }
        })
        searchView.setOnCloseListener {
            presenter.onSearchButtonClicked()
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.search_item -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }

    override fun updateAdapter(articles: List<ResultsItem>) {
        adapter = RecyclerAdapter(context, articles)
        recycler_id.adapter = adapter
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

    override fun showProgressBar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressBar() {
        recycler_id.visibility = View.VISIBLE
        swipe_container.isRefreshing = false
    }
}