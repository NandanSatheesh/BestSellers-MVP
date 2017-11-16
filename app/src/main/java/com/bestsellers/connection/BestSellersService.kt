package com.bestsellers.connection

import com.bestsellers.model.BestSellersResult
import com.bestsellers.model.BookGenresResult
import com.bestsellers.model.HistoryBestSellersResult
import com.bestsellers.model.ReviewsResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient


/**
 * Created by Rafaela Araujo
 * on 06/11/2017.
 */
class BestSellersService {

    private val bestSellersApi: BestSellersApi

    init {
        val defaultHttpClient = OkHttpClient.Builder().addInterceptor({ chain ->
            val request = chain.request().newBuilder().addHeader("api-key", "f60617ae371d44d898018a1e77a89a8d").build()
            chain.proceed(request)
        }).build()


        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(defaultHttpClient)
                .build()


        bestSellersApi = retrofit.create(BestSellersApi::class.java)
    }

    fun getHistoryBestSellers(): Call<HistoryBestSellersResult> {
        return bestSellersApi.getHistoryBestSellers()
    }

    fun getGenreList(): Call<BookGenresResult> {
        return bestSellersApi.getBookGenresList()
    }

    fun getBestSellerByNameList(name:String): Call<BestSellersResult> {
        return bestSellersApi.getBestSellerByNameList(name)
    }

    fun getReviews(tittle:String): Call<ReviewsResult> {
        return bestSellersApi.getReviews(tittle)
    }
}