package com.ad.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ad.moviesapp.adapter.MovieAdpater
import com.ad.moviesapp.api.ApiClient
import com.ad.moviesapp.api.ApiService
import com.ad.moviesapp.response.MoviesListResponse
import com.ad.moviesapp.utlis.Constants.POSTER_BASEURL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.Collections

class MainActivity : AppCompatActivity() {
    lateinit var recylerView: RecyclerView
    lateinit var myAdapter: MovieAdpater
    private val api:ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerView = findViewById(R.id.rvMovie)
        val callMovieApi = api.getPopularMovies(100)
        callMovieApi.enqueue(object : Callback<MoviesListResponse> {
            override fun onResponse(call: Call<MoviesListResponse>, response: Response<MoviesListResponse>) {
               var responseBody = response.body()
              val movieList = responseBody?.results!!
                for (myData in movieList) {
                    Log.d("TAG", "onResponse: " + POSTER_BASEURL+myData.posterPath)
                }
                Collections.shuffle(movieList)
                myAdapter = MovieAdpater(this@MainActivity,movieList)
                recylerView.adapter = myAdapter
                recylerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}