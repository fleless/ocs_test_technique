package tn.ocs.ocs_testtechnique.ui.HomeScene

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import tn.ocs.ocs_testtechnique.R
import tn.ocs.ocs_testtechnique.common.application.App
import tn.ocs.ocs_testtechnique.common.constants.QUERY_KEY
import tn.ocs.ocs_testtechnique.databinding.ActivityMainBinding
import tn.ocs.ocs_testtechnique.ui.BaseActivity
import tn.ocs.ocs_testtechnique.ui.HomeScene.fragments.ListMoviesFragment

class HomeActivity : BaseActivity() {

    lateinit var viewModel : HomeViewModel
    lateinit var binding : ActivityMainBinding
    lateinit var container : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        container = binding.container
        viewModel.fragmentInView.observe(this, Observer { state ->
          displayFragment(state)
        })
        viewModel.fragmentInView.value = "list"
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        if(!App.sharedPreference.getValueString(QUERY_KEY,"").equals("")){
            viewModel.getMovies(App.sharedPreference.getValueString(QUERY_KEY,"")!!)
            toolbar.subtitle = App.sharedPreference.getValueString(QUERY_KEY,"")!!
        }
    }

    fun displayFragment(state: String){
        if(state.equals("list")){
            supportFragmentManager.beginTransaction().replace(R.id.container, ListMoviesFragment()).commit()
        }else if (state.equals("detail")) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu!!.findItem(R.id.menu_search)
        if(searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val searchEditText = searchView.findViewById<SearchView.SearchAutoComplete>(R.id.search_src_text)
            searchEditText.hint = "Search for a movie.."
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    App.sharedPreference.saveString(QUERY_KEY,query!!)
                    toolbar.subtitle = query
                    searchItem.collapseActionView()
                    viewModel.getMovies(query)
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when (itemView) {
            R.id.menu_search -> return true
        }

        return false
    }

}
