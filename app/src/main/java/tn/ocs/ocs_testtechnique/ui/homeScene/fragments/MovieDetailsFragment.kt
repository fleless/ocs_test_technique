package tn.ocs.ocs_testtechnique.ui.homeScene.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater

import tn.ocs.ocs_testtechnique.R
import tn.ocs.ocs_testtechnique.common.application.App
import tn.ocs.ocs_testtechnique.common.constants.CONTENT_KEY
import tn.ocs.ocs_testtechnique.data.model.Content
import tn.ocs.ocs_testtechnique.databinding.FragmentMovieDetailsBinding
import tn.ocs.ocs_testtechnique.ui.homeScene.HomeViewModel
import tn.ocs.ocs_testtechnique.ui.moviePlayerScene.MoviePlayerActivity


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: HomeViewModel
    private val content:Content = App.sharedPreference.getValueContent(CONTENT_KEY)!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("argument", App.sharedPreference.getValueContent(CONTENT_KEY)!!.title[0].value)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        binding.content = content
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
            viewModel.getPitch(content.detaillink)
            viewModel.pitch.observe(this, Observer { state ->
                binding.detailLinkMovie.text = state.contents.pitch
                if(binding.detailLinkMovie.text == ""){
                    binding.detailLinkMovie.text = state.contents.seasons[0].pitch
                }
            })
        }
        binding.playBtn.setOnClickListener {
            val intent: Intent = Intent(activity!!, MoviePlayerActivity::class.java)
            startActivity(intent)
         }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater
            .from(context).inflateTransition(
                android.R.transition.move
            )
        setHasOptionsMenu(false)
        Log.d("content", content.detaillink)
    }

}
