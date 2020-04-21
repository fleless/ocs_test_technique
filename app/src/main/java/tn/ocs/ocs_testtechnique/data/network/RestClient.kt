package tn.ocs.ocs_testtechnique.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import tn.ocs.ocs_testtechnique.common.application.App
import tn.ocs.ocs_testtechnique.data.network.apiServices.MovieServices


object RestClient {
    fun create(): MovieServices {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            //am using VPN so this interceptor will not work properly
            //.addInterceptor(ConnectivityInterceptor(App.instance))
            .build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        return retrofit.create(MovieServices::class.java)
    }
}