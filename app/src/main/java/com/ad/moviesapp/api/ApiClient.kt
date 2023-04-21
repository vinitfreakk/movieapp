package com.ad.moviesapp.api

import com.ad.moviesapp.utlis.Constants.API_KEY
import com.ad.moviesapp.utlis.Constants.BASEURL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

private lateinit var retrofit: Retrofit

    //modifying the url by adding api key to it.
    private val requestInterceptor = Interceptor {chain ->
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }

    /* In web development, an interceptor is like a middleman between a client and server. It helps modify or handle data going back and forth between them.

For example, imagine you want to send a request to a server to get some data. You might use an interceptor to add extra information to that request, like your authentication credentials. The interceptor will intercept the request before it gets sent to the server and add the necessary information.

Once the server receives the request, it might send back some data in response. An interceptor can also intercept that response and modify it before it gets back to the client. For example, it could format the data in a specific way or add some extra information.

So, interceptors are just a way to modify or handle data going back and forth between a client and server. They're like a middleman that helps you add or change things as needed.*/

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .callTimeout(60,TimeUnit.SECONDS)
        .build()

    fun getClient():Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }



}