package com.bestsellers.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bestsellers.R
import com.bestsellers.model.Book
import com.bestsellers.util.loadUrl
import kotlinx.android.synthetic.main.best_seller_item.view.*

/**
 * Created by Rafaela Araujo
 * on 05/03/2018.
 */
class FavoriteAdapter(
        private val favorites: List<Book>) :
        RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) = holder.bind(favorites[position])

    override fun getItemCount(): Int = favorites.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(item: Book) = with(itemView) {
            bookTittle.text = item.title
            bookAuthor.text = item.contributor
            bookImage.loadUrl(item.book_image)
        }
    }

}