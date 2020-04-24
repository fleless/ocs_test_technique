package tn.ocs.ocs_testtechnique.ui.homeScene.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.ocs.ocs_testtechnique.R
import tn.ocs.ocs_testtechnique.data.model.Content
import tn.ocs.ocs_testtechnique.data.network.IMAGE_URL
import tn.ocs.ocs_testtechnique.databinding.MovieItemBinding

open class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.transactionItemHolder> {

    var  listMovies: List<Content>
    var  context: Context
    private val VIEW_TYPE = 1

    constructor(context: Context, moviesList: List<Content> ){
        this.context = context
        this.listMovies = moviesList
    }

    override fun getItemViewType(position: Int): Int { return VIEW_TYPE }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): transactionItemHolder {
      //  if (viewType == VIEW_TYPE) {
            val dataBinding: MovieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_item, parent, false
            )
            return transactionItemHolder(dataBinding)
       // }
    }

    override fun onBindViewHolder(holder: transactionItemHolder, position: Int) {
        //holder.dataBinding.content = listMovies.get(position)
        holder.dataBinding.content = listMovies.get(index = position)
    }

    override fun getItemCount(): Int { return listMovies.size }

     class transactionItemHolder(dataBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(dataBinding.getRoot()) {

         var dataBinding: MovieItemBinding

        init {
            this.dataBinding = dataBinding
        }
    }
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageurl: String) {
            Glide.with(view.context)
                .load(IMAGE_URL+imageurl)
                .placeholder(R.drawable.ocs_orange_splash)
                .into(view)
        }
    }


}