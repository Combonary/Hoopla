package com.example.hoopla.presentation.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hoopla.R
import com.example.hoopla.databinding.MovieListItemViewHolderBinding
import com.example.hoopla.domain.model.Movie
import com.example.hoopla.utils.Constants

/**
 * adapter class for movies list
 */
class MovieListAdapter(
    private val movieList: List<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var itemClickedListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = MovieListItemViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.itemViewBinding.movieNameTextView.text = movieList[position].title
        holder.itemViewBinding.moviePosterImageView.load(
            Constants.IMAGE_URL.replace(
                Constants.ART_KEY,
                movieList[position].artKey
            )
        ) {
            placeholder(R.drawable.movie)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setOnItemClickedListener(itemClickedListener: ClickListener) {
        this.itemClickedListener = itemClickedListener
    }

    fun getMovieTitleId(position: Int): Int {
        return movieList[position].titleId
    }

    inner class MovieListViewHolder(val itemViewBinding: MovieListItemViewHolderBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root), OnClickListener {

        init {
            if (itemClickedListener != null) {
                itemViewBinding.root.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            if (v != null) {
                itemClickedListener?.onItemClicked(v, adapterPosition)
            }
        }
    }

    interface ClickListener {
        fun onItemClicked(view: View, position: Int)
    }
}