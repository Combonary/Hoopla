package com.example.hoopla.presentation.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hoopla.R
import com.example.hoopla.databinding.ActivityMovieListBinding
import com.example.hoopla.domain.model.Movie
import com.example.hoopla.presentation.moviedetail.MovieDetailActivity
import com.example.hoopla.presentation.movielist.adapter.MovieListAdapter
import com.example.hoopla.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private lateinit var movieListBinding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        movieListBinding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(movieListBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel: MovieListViewModel by viewModels()
        initObservers(viewModel)
    }

    private fun initObservers(viewModel: MovieListViewModel) {
        val loadingObserver = Observer<Boolean> {
            movieListBinding.movieListProgressBar.isVisible = it
        }

        val movieListObserver = Observer<List<Movie>> { movieList ->
            val adapter = MovieListAdapter(movieList)
            movieListBinding.movieListRecyclerView.layoutManager = GridLayoutManager(this, 2)
            movieListBinding.movieListRecyclerView.adapter = adapter
            adapter.setOnItemClickedListener(object : MovieListAdapter.ClickListener {
                override fun onItemClicked(view: View, position: Int) {
                    navigateToMovieDetailActivity(adapter.getMovieTitleId(position))
                }
            })
        }

        val errorObserver = Observer<Boolean> {
            movieListBinding.movieListErrorTextView.isVisible = it
        }

        viewModel.isLoading.observe(this, loadingObserver)
        viewModel.moviesList.observe(this, movieListObserver)
        viewModel.serverError.observe(this, errorObserver)
    }

    private fun navigateToMovieDetailActivity(id: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constants.TITLE_ID, id)
        startActivity(intent)
    }
}