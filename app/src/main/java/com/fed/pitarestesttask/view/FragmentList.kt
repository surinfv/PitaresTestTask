package com.fed.pitarestesttask.view

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.SearchView
import com.fed.pitarestesttask.R
import com.fed.pitarestesttask.model.ResultsItem
import com.fed.pitarestesttask.presenter.FragmentListInterface
import com.fed.pitarestesttask.presenter.Presenter
import com.fed.pitarestesttask.presenter.PresenterInterface
import kotlinx.android.synthetic.main.fragment_elements_list.*


class FragmentList : Fragment(), FragmentListInterface {
    private val TAG = "FragmentList"
    private lateinit var presenter: PresenterInterface
    private var adapter: RecyclerAdapter? = null
    private var isLoading = false

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
        recycler_view.layoutManager = LinearLayoutManager(context)
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
        if (adapter == null) {
            adapter = RecyclerAdapter(context, articles)
            recycler_view.adapter = adapter
        } else {
            adapter?.setArticles(articles)
        }
        adapter?.notifyDataSetChanged()
        setRecyclerViewScrollListener()
    }

    override fun showWrongResponseDialog() {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.apply {
            setTitle("Error")
            setMessage("wrong response from server. application will try reload articles.")
            setButton(AlertDialog.BUTTON_POSITIVE, "got it", { _, _ ->
                presenter.onWrongResponseDialogButtonClicked()
            })
        }.show()
    }

    override fun showProgressBar() {
        swipe_container.isRefreshing = true
    }

    override fun hideProgressBar() {
        recycler_view.visibility = View.VISIBLE
        swipe_container.isRefreshing = false
    }

    override fun setLoadingFlag(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    private fun setRecyclerViewScrollListener() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition = (recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1
                val totalItemCount = recyclerView.layoutManager.itemCount
                Log.i(TAG, "totalItemCount -> $totalItemCount ; lastVisibleItemPosition -> $lastVisibleItemPosition")
                if (!isLoading
                        && lastVisibleItemPosition > totalItemCount - 3) {
                    Log.i(TAG, "lastItemShown()")
                    presenter.lastItemShown()
                }
            }
        })
    }
}