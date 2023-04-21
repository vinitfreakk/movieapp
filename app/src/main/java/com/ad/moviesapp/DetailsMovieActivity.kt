package com.ad.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.ad.moviesapp.api.ApiClient
import com.ad.moviesapp.api.ApiService
import com.ad.moviesapp.response.MovieDeatilsResponse
import com.ad.moviesapp.utlis.Constants.POSTER_BASEURL
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsMovieActivity : AppCompatActivity() {
    private val api : ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)
         val movieId = intent.getIntExtra("id",1)
        val bgImg = findViewById<ImageView>(R.id.bg_img)
        val imgMovie = findViewById<ShapeableImageView>(R.id.imgMovie)
        val movieName = findViewById<TextView>(R.id.tvMovieName)
        val movieTagLine = findViewById<TextView>(R.id.tvMovieTagLine)
        val MovieRating = findViewById<TextView>(R.id.tvMovieRating)
        val MovieDateRelease = findViewById<TextView>(R.id.tvMovieDateRelease)
        val MovieRuntime = findViewById<TextView>(R.id.tvMovieRuntime)
        val MovieBudget = findViewById<TextView>(R.id.tvMovieBudget)
        val MovieRevenue = findViewById<TextView>(R.id.tvMovieRevenue)
        val MovieOverview = findViewById<TextView>(R.id.tvMovieOverview)
        val progressbar = findViewById<ProgressBar>(R.id.prgBarMovies)

        val callDetailsMovieActivity = api.getMoviedeatalies(movieId)
        callDetailsMovieActivity.enqueue(object : Callback<MovieDeatilsResponse?> {
            override fun onResponse(call: Call<MovieDeatilsResponse?>, response: Response<MovieDeatilsResponse?>){
                var responseBody = response.body()
                Picasso.get().load(POSTER_BASEURL+ responseBody!!.posterPath).into(bgImg)
                Picasso.get().load(POSTER_BASEURL+ responseBody!!.posterPath).into(imgMovie)
                movieName.text = responseBody.originalTitle
                movieTagLine.text = responseBody.tagline
                MovieRating.text  = responseBody.voteAverage.toString()
                MovieDateRelease.text = responseBody.releaseDate
                MovieRuntime.text = responseBody.runtime.toString()
                MovieBudget.text = responseBody.budget.toString()
                MovieRevenue.text = responseBody.revenue.toString()
                MovieOverview.text = responseBody.overview
                progressbar.visibility = View.GONE
            }

            override fun onFailure(call: Call<MovieDeatilsResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}