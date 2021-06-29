package com.liusaprian.moviecatalogue.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liusaprian.moviecatalogue.MyApplication
import com.liusaprian.moviecatalogue.adapter.MovieAdapter
import com.liusaprian.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.liusaprian.moviecatalogue.utils.ResponseConfig
import com.liusaprian.moviecatalogue.viewmodel.MovieViewModel
import com.liusaprian.moviecatalogue.viewmodel.ViewModelFactory
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding

    @Inject
    lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        const val TYPE = "type"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ((activity as FavoriteActivity).application as MyApplication).appComponent.favoriteComponent().create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)

        val movieAdapter = MovieAdapter()

        val category =
            if(arguments?.getString(TYPE) == ResponseConfig.MOVIE) viewModel.getFavoriteMovies()
            else viewModel.getFavoriteTvShows()

        category.observe(requireActivity(), { movies ->
            movies?.let {
                movieAdapter.submitList(it)
                movieAdapter.notifyDataSetChanged()
                emptyList(false)
            }
            if(movies.isNullOrEmpty()) emptyList(true)
            showLoading(false)
        })

        binding.rvFavorite.adapter = movieAdapter
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }

    private fun emptyList(state: Boolean) {
        binding.noFavMovies.visibility = if(state) View.VISIBLE else View.GONE
    }
}