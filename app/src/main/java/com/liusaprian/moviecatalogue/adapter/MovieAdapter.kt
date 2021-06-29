package com.liusaprian.moviecatalogue.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.IMAGE_BASE_URL
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.IMAGE_FILE_SIZE
import com.liusaprian.moviecatalogue.databinding.MovieItemBinding
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.ui.detail.MovieDetailActivity

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("$IMAGE_BASE_URL/$IMAGE_FILE_SIZE${data.poster}")
                    .placeholder(R.drawable.ic_loading)
                    .into(poster)
                title.text = data.title
                date.text = if(data.isMovie) data.date else root.resources.getString(R.string.first_air_date_text, data.date)
                overview.text = data.overview
                itemView.setOnClickListener {
                    val toDetailActivity = Intent(itemView.context, MovieDetailActivity::class.java)
                    toDetailActivity.putExtra(MovieDetailActivity.EXTRA_DATA, data)
                    itemView.context.startActivity(toDetailActivity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}