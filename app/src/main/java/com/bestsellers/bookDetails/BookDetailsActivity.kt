package com.bestsellers.bookDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebView
import com.bestsellers.bestSellers.R
import com.bestsellers.model.Book
import com.bestsellers.util.Constants.Companion.BOOK
import com.bestsellers.util.loadUrl
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.best_seller_item.*
import android.webkit.WebViewClient
import com.bestsellers.common.BaseActivity

class BookDetailsActivity : BaseActivity(), BookDetailsContract.View {

    override lateinit var presenter: BookDetailsContract.Presenter
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        book = intent.extras.getSerializable(BOOK) as Book
        presenter = BookDetailsPresenter(this)
        presenter.getBookReview(book.title)
        configureActionBar(book.title)
        setBookInformations()
    }

    private fun setBookInformations() {
        bookTittle.text = book.title
        bookAuthor.text = book.contributor
        bookDescription.text = book.description
        bookImage.loadUrl(book.book_image)
    }

    fun openAmazonProductUrl(v: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(book.amazon_product_url)))
    }

    override fun loadBookReview(reviewUrl: String) {
        llBookData.visibility = GONE
        btnBuyBook.visibility = VISIBLE

        reviewWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                hideLoading()
            }
        }
        reviewWebView.loadUrl(reviewUrl)
    }

    override fun showNoReviewsView() {
        hideLoading()
        llBookData.visibility = VISIBLE
        empityReviewMessage.visibility = VISIBLE
    }

    override fun showErrorMessage() {
        empityReviewMessage.visibility = VISIBLE
        empityReviewMessage.text = getString(R.string.error_loading_review)
    }

    override fun showLoading() {
        progressReview.visibility = VISIBLE
    }

    override fun hideLoading() {
        progressReview.visibility = GONE
    }

}