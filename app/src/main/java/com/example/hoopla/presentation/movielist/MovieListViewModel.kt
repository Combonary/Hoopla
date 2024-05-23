package com.example.hoopla.presentation.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoopla.domain.model.Movie
import com.example.hoopla.domain.model.ServerResult
import com.example.hoopla.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading
    private val _moviesList = MutableLiveData<List<Movie>>()
    var moviesList = _moviesList
    private val _serverError = MutableLiveData(false)
    val serverError = _serverError

    init {
        viewModelScope.launch {
            loadMovieList()
        }
    }

    private suspend fun loadMovieList() {
        _isLoading.value = true
        val result = moviesRepository.getMovies()

        if (result.status == ServerResult.Status.SUCCESS) {
            _isLoading.value = false
            result.data?.let { _moviesList.value = it.toList() }
        }

        if (result.status == ServerResult.Status.ERROR) {
            _isLoading.value = false
            _serverError.value = true
        }
    }
}