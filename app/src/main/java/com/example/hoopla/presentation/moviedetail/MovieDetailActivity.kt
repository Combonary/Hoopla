package com.example.hoopla.presentation.moviedetail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import coil.load
import com.example.hoopla.R
import com.example.hoopla.databinding.ActivityMovieDetailBinding
import com.example.hoopla.domain.model.MovieDetail
import com.example.hoopla.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        movieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(movieDetailBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpSupportActionBar()

        val titleId = intent.getIntExtra(Constants.TITLE_ID, 0)

        val movieDetailViewModel by viewModels<MovieDetailViewModel>(
            extrasProducer = {
                defaultViewModelCreationExtras.withCreationCallback<MovieDetailViewModel.MovieDetailViewModelFactory> { factory ->
                    factory.create(titleId)
                }
            }
        )

        initObservers(movieDetailViewModel)
    }

    private fun setUpSupportActionBar() {
        setSupportActionBar(movieDetailBinding.movieDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initObservers(movieDetailViewModel: MovieDetailViewModel) {

        val loadingObserver = Observer<Boolean> {
            movieDetailBinding.movieDetailProgressBar.isVisible = it
        }

        val movieDetailObserver = Observer<MovieDetail> { movieDetail ->

            movieDetailBinding.movieDetailToolbar.title = movieDetail.title

            movieDetailBinding.movieDetailPosterImageView.load(
                Constants.IMAGE_URL.replace(
                    Constants.ART_KEY,
                    movieDetail.artKey
                )
            )

            movieDetailBinding.movieDetailTitleTextView.text = movieDetail.title

            movieDetailBinding.movieDetailArtistsTextView.text =
                resources.getString(R.string.cast_text)
                    .replace(Constants.PLACEHOLDER, movieDetailViewModel.getCast())

            movieDetailBinding.movieDetailGenresTextView.text =
                resources.getString(R.string.genres_text)
                    .replace(Constants.PLACEHOLDER, movieDetailViewModel.getGenres())

            movieDetailBinding.movieDetailSynopsisTextView.text = movieDetail.synopsis

            movieDetailBinding.movieDetailPlayButton.setOnClickListener {
                showPlayButtonToast(
                    resources.getString(R.string.play_button_toast_text)
                        .replace(Constants.PLACEHOLDER, movieDetail.title)
                )
            }
        }

        val errorObserver = Observer<Boolean> {
            movieDetailBinding.movieDetailErrorTextView.isVisible = it
            movieDetailBinding.movieDetailTitleContainer.isVisible = !it
            movieDetailBinding.movieDetailHorizontalSeparator.isVisible = !it
        }

        movieDetailViewModel.isLoading.observe(this, loadingObserver)
        movieDetailViewModel.movie.observe(this, movieDetailObserver)
        movieDetailViewModel.serverError.observe(this, errorObserver)
    }

    private fun showPlayButtonToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}