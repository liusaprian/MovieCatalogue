package com.liusaprian.moviecatalogue.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.liusaprian.moviecatalogue.MyApplication
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.adapter.MovieAdapter
import com.liusaprian.moviecatalogue.databinding.FragmentMovieBinding
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.MOVIE
import com.liusaprian.moviecatalogue.utils.SortUtils
import com.liusaprian.moviecatalogue.viewmodel.MovieViewModel
import com.liusaprian.moviecatalogue.viewmodel.ViewModelFactory
import com.liusaprian.moviecatalogue.vo.Resource
import com.liusaprian.moviecatalogue.vo.Status
import javax.inject.Inject

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val movieAdapter = MovieAdapter()

    @Inject
    lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        const val TYPE = "type"
        const val DEFAULT = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ((activity as MainActivity).application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = arguments?.getString(TYPE) as String
        setMovieSortOrder(category, DEFAULT)

        binding.rvCatalogue.adapter = movieAdapter

        binding.sortAsc.setOnClickListener {
            setMovieSortOrder(category, SortUtils.ASCENDING_RATING)
        }
        binding.sortDesc.setOnClickListener {
            setMovieSortOrder(category, SortUtils.DESCENDING_RATING)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }

    private fun setMovieSortOrder(type: String, sort: String) {
        if(type == MOVIE) viewModel.getMovies(sort).observe(requireActivity(), movieObserver)
        else viewModel.getTvShows(sort).observe(requireActivity(), movieObserver)
    }

    private val movieObserver = Observer<Resource<PagedList<Movie>>> { movies ->
        movies?.let {
            when(it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    movieAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(
                        context,
                        getString(R.string.error_string),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}