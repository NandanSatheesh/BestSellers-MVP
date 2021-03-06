package com.bestsellers.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bestsellers.R
import com.bestsellers.bookdetails.BookDetailsActivity
import com.bestsellers.data.model.Book
import com.bestsellers.util.BOOK
import com.bestsellers.util.ext.launchActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.empty_state_view.*
import org.koin.android.ext.android.inject

/**
 * Created by rafaela.araujo on 27/02/18.
 */
class FavoriteFragment : Fragment(), FavoriteContract.View {

    override val presenter by inject<FavoriteContract.Presenter>()
    private var favoriteList: List<Book> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        presenter.view = this
        return inflater.inflate(R.layout.activity_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.getFavoriteBooks()
    }

    private fun loadRecyclerView() {
        recyclerFavorite.layoutManager = GridLayoutManager(context, 2)
        recyclerFavorite.adapter = FavoriteAdapter(favoriteList, this::showBookDetails)
        empityStateLayout.visibility = GONE
        recyclerFavorite.visibility = VISIBLE

    }

    override fun showErrorMessage() {
        hideLoading()
        recyclerFavorite.visibility = GONE
        empityStateLayout.visibility = VISIBLE
        txt_message_emptyState.text = getString(R.string.error_loading_data)
    }

    override fun showLoading() {
        progressFavorite.visibility = VISIBLE
    }

    override fun hideLoading() {
        progressFavorite.visibility = GONE
    }

    override fun  showFavoriteBooks(list: List<Book>) {
        favoriteList = list
        loadRecyclerView()
    }

    private fun showBookDetails(book: Book) {
        activity?.launchActivity<BookDetailsActivity> { putExtra(BOOK, book) }
    }
}