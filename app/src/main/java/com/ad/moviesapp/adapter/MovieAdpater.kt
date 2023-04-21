package com.ad.moviesapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ad.moviesapp.DetailsMovieActivity

import com.ad.moviesapp.R
import com.ad.moviesapp.response.MoviesListResponse
import com.ad.moviesapp.utlis.Constants.POSTER_BASEURL
import com.squareup.picasso.Picasso

class MovieAdpater(val context: Context, val MoviesList : List<MoviesListResponse.Result>):RecyclerView.Adapter<MovieAdpater.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = MoviesList[position]
        holder.MovieName.text =currentItem.title
        holder.Rating.text = currentItem.voteAverage.toString()
        holder.language.text = currentItem.originalLanguage
        holder.realsesdate.text = currentItem.releaseDate
       // Picasso.get().load(R.drawable.hira).into(holder.imagView)
        Picasso.get().load(POSTER_BASEURL+currentItem.posterPath).into(holder.imagView)
        holder.dataroot.setOnClickListener {
            val intent = Intent(context,DetailsMovieActivity::class.java)
            intent.putExtra("id",currentItem.id)
            context.startActivity(intent)
        }



    }


    override fun getItemCount(): Int {
        return MoviesList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var MovieName : TextView
        var Rating: TextView
        var realsesdate: TextView
        var language: TextView
        var imagView: ImageView
        var dataroot:ConstraintLayout


        init {
                imagView = itemView.findViewById(R.id.ImgMovie)
                MovieName =itemView.findViewById(R.id.tvMovieName)
                Rating = itemView.findViewById(R.id.tvRate)
                realsesdate = itemView.findViewById(R.id.tvMovieDateRelease)
                language = itemView.findViewById(R.id.tvLang)
               dataroot=itemView.findViewById(R.id.dataRoot)

        }



    }
}