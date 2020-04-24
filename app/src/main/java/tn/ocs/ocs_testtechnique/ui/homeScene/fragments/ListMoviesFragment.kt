package tn.ocs.ocs_testtechnique.ui.homeScene.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import tn.ocs.ocs_testtechnique.R
import tn.ocs.ocs_testtechnique.common.application.App
import tn.ocs.ocs_testtechnique.common.constants.CONTENT_KEY
import tn.ocs.ocs_testtechnique.common.constants.QUERY_KEY
import tn.ocs.ocs_testtechnique.common.extensions.RecyclerItemClickListenr
import tn.ocs.ocs_testtechnique.data.model.Movies
import tn.ocs.ocs_testtechnique.databinding.FragmentListMoviesBinding
import tn.ocs.ocs_testtechnique.ui.homeScene.HomeViewModel
import tn.ocs.ocs_testtechnique.ui.homeScene.adapter.MoviesAdapter


class ListMoviesFragment : Fragment() {

    private lateinit var binding: FragmentListMoviesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private var moviesAdapter: MoviesAdapter ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_movies, container, false)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
            viewModel.movies.observe(this, Observer { state ->
                updateUI(state)
            })
            viewModel.errorSearch.observe(this, Observer { state ->
                if(state) { errorSearchFunction() }
            })
        }

        recyclerView = binding.gridLayout
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = moviesAdapter
        recyclerView.addOnItemTouchListener(RecyclerItemClickListenr(activity!!, recyclerView, object : RecyclerItemClickListenr.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        App.sharedPreference.saveContent(CONTENT_KEY,
                            viewModel.movies.value!!.contents[position]
                        )
                        viewModel.fragmentInView.value = "detail"
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                    }
                })
        )
        return binding.root
    }

    private fun updateUI(state: Movies){
        if(state.count > 0) {
            binding.gridLayout.visibility = View.VISIBLE
            binding.emptyListText.visibility = View.GONE
            moviesAdapter = MoviesAdapter(context!!, state.contents)
            recyclerView.adapter = moviesAdapter
            moviesAdapter!!.notifyDataSetChanged()
        }else {
            binding.gridLayout.visibility = View.GONE
            binding.emptyListText.visibility = View.VISIBLE
            binding.emptyListText.text = resources.getString(R.string.zeroMoviesFound)
        }
    }

    private fun errorSearchFunction() {
        binding.gridLayout.visibility = View.GONE
        binding.emptyListText.visibility = View.VISIBLE
        binding.emptyListText.text = resources.getString(R.string.errorSearch)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.toolbar.subtitle = App.sharedPreference.getValueString(QUERY_KEY,"")
        if(!App.sharedPreference.getValueString(QUERY_KEY,"").equals("")){
            viewModel.getMovies(App.sharedPreference.getValueString(QUERY_KEY,"")!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}
