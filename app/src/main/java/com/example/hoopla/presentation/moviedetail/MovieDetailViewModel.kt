package com.example.hoopla.presentation.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoopla.domain.model.MovieDetail
import com.example.hoopla.domain.model.ServerResult
import com.example.hoopla.domain.repository.MoviesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MovieDetailViewModel.MovieDetailViewModelFactory::class)
class MovieDetailViewModel @AssistedInject constructor(
    @Assisted private val titleId: Int,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetail>()
    val movie = _movie
    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading
    private val _serverError = MutableLiveData(false)
    val serverError = _serverError

    @AssistedFactory
    interface MovieDetailViewModelFactory {
        fun create(id: Int): MovieDetailViewModel
    }

    init {
        viewModelScope.launch {
            loadMovie(titleId)
        }
    }

    private suspend fun loadMovie(titleId: Int) {
        _isLoading.value = true
        val result = moviesRepository.getMovie(titleId)
        if (result.status == ServerResult.Status.SUCCESS) {
            _isLoading.value = false
            result.data.let { _movie.value = it }
        }

        if (result.status == ServerResult.Status.ERROR) {
            _isLoading.value = false
            _serverError.value = true
        }
    }

    fun getCast(): String {
        val artists = movie.value?.artists
        val cast: MutableList<String> = mutableListOf()

        if (artists != null) {
            for (artist in artists) {
                cast.add(artist.name)
            }
        }
        return cast.toTypedArray().joinToString()
    }

    fun getGenres(): String {
        val genres = movie.value?.genres
        val movieGenres: MutableList<String> = mutableListOf()

        if (genres != null) {
            for (genre in genres) {
                movieGenres.add(genre.name)
            }
        }
        return movieGenres.toTypedArray().joinToString()
    }
}