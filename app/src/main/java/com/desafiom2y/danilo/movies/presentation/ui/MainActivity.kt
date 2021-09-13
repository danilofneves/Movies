package com.desafiom2y.danilo.movies.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.desafiom2y.danilo.movies.R
import com.desafiom2y.danilo.movies.core.ViewModelFactory
import com.desafiom2y.danilo.movies.data.exception.ResponseError
import com.desafiom2y.danilo.movies.databinding.ActivityMainBinding
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.presentation.extensions.*
import com.desafiom2y.danilo.movies.presentation.viewmodels.MainViewModel
import com.desafiom2y.danilo.movies.data.Resource
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val progressDialog by lazy { createProgressDialog() }
    private lateinit var binding: ActivityMainBinding
    private val moviesSimilarListAdapter by lazy { MovieSimilarListAdapter()}
    private var vote_count = 0

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = viewModelFactory.create(MainViewModel::class.java)
        binding.listMovies.rvMovies.adapter = moviesSimilarListAdapter
        viewModel.getMovieDetail()
        observe(viewModel.movieLiveData, ::handleGetMovie)
        observe(viewModel.error, ::handleError)

        setContentView(binding.root)
    }

    private fun handleGetMovie(status: Resource<Movie>) {
        when (status) {
            is Resource.Loading -> {
                progressDialog.show()
            }
            is Resource.Success -> status.data?.let {
                progressDialog.dismiss()
                setupMovieDetail(it)
                setupListener()
                handleGetSimilarMovies()
            }
            is Resource.DataError -> {
                progressDialog.dismiss()
                viewModel.notifyFailure(status.error)
            }
        }
    }

    private fun setupMovieDetail(movie: Movie){
        vote_count = movie.vote_count
        if(viewModel.isFavorite()){
            binding.favorite()
            vote_count++
        }else {
            binding.disfavorite()
        }
        binding.tvMovieNameLg.text = movie.title
        binding.tvMovieFavoriteCount.text = vote_count.toString()
        binding.tvMoviePopularityCount.text = movie.popularity.toString()
        Glide.with(binding.root.context)
            .load(movie.poster_path)
            .placeholder(R.drawable.photo_load)
            .fallback(R.drawable.broken_image)
            .into(binding.ivMoviePosterLg)
    }

    private fun setupListener(){
        binding.ivFavorite.setOnClickListener {
            if(viewModel.isFavorite()){
                binding.disfavorite()
                viewModel.disfavorite()
                vote_count--
                binding.tvMovieFavoriteCount.text = vote_count.toString()
            }
            else{
                binding.favorite()
                viewModel.favorite()
                vote_count++
                binding.tvMovieFavoriteCount.text = vote_count.toString()
            }
        }
    }

    private fun handleGetSimilarMovies() {
        lifecycleScope.launch{
            viewModel.moviesSimilarPaging().collectLatest { response ->
                moviesSimilarListAdapter.submitData(response)
            }
        }
    }

    private fun handleError(responseError: ResponseError) {
        val msg:String = when (responseError) {
            is ResponseError.ClientErrorException -> getString(R.string.clientError)
            is ResponseError.IOErrorException -> getString(R.string.ioError)
            is ResponseError.ServerErrorException -> getString(R.string.serverError)
        }
        createDialog {
            setMessage(msg)
        }.show()
    }


}