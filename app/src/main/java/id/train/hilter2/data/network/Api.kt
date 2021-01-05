package id.train.hilter2.data.network

import id.train.hilter2.data.network.entity.BlogNetworkEntity
import retrofit2.http.GET

interface Api {

    @GET("blogs")
    suspend fun getData(): List<BlogNetworkEntity>
}