package com.liusaprian.moviecatalogue.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.liusaprian.moviecatalogue.MyApplication
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.databinding.ActivityDetailBinding
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.IMAGE_BASE_URL
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.IMAGE_FILE_SIZE
import com.liusaprian.moviecatalogue.viewmodel.MovieViewModel
import com.liusaprian.moviecatalogue.viewmodel.ViewModelFactory
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var data: Movie

    @Inject
    lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Movie>(EXTRA_DATA)?.let {
            data = it
        }

        setFavoriteState(data.isFavorite)

        Glide.with(this)
            .load("$IMAGE_BASE_URL/$IMAGE_FILE_SIZE${data.backdrop}")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(binding.detailBackdrop)
        Glide.with(this)
            .load("$IMAGE_BASE_URL/$IMAGE_FILE_SIZE${data.poster}")
            .into(binding.detailPoster)
        binding.detailTitle.text = data.title
        binding.detailDate.text = data.date
        binding.detailGenres.text = data.genres
        binding.detailRating.text = getString(R.string.rating, String.format("%.1f", data.rating/2))
        binding.detailOverview.text = data.overview

        binding.favoriteBtn.setOnClickListener {
            viewModel.switchFavoriteState(data)
            setFavoriteState(data.isFavorite)
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun setFavoriteState(favorite: Boolean) {
        binding.favoriteBtn.setImageResource(
            if(favorite) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
    }
}