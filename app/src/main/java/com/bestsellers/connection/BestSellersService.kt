package com.bestsellers.connection

import com.bestsellers.model.BestSellersResult
import com.bestsellers.model.BookGenresResult
import com.bestsellers.model.HistoryBestSellersResult
import com.bestsellers.model.ReviewsResult
import com.bestsellers.util.API_KEY
import com.bestsellers.util.AUTORIZATION
import com.bestsellers.util.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by Rafaela Araujo
 * on 06/11/2017.
 */


open class BestSellersService {

    private val bestSellersApi: BestSellersApi

    init {
        val defaultHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader(API_KEY, AUTORIZATION).build()
            chain.proceed(request)
        }.build()


        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(defaultHttpClient)
                .build()

        bestSellersApi = retrofit.create(BestSellersApi::class.java)
    }

    fun getHistoryBestSellers(): Observable<HistoryBestSellersResult> =
            bestSellersApi.getHistoryBestSellers()

    fun getGenreList(): Observable<BookGenresResult> = bestSellersApi.getBookGenresList()

    fun getBestSeller(name:String): Observable<BestSellersResult> =
            bestSellersApi.getBestSellerByNameList(name)

    fun getBookReview(tittle:String): Observable<ReviewsResult> = bestSellersApi.getReviews(tittle)
}