package tn.ocs.ocs_testtechnique.ui.HomeScene

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tn.ocs.ocs_testtechnique.common.extensions.NoConnectivityException
import tn.ocs.ocs_testtechnique.data.model.Movies
import tn.ocs.ocs_testtechnique.data.repository.Repository
import tn.ocs.ocs_testtechnique.data.repository.RepositoryProvider
import java.net.SocketTimeoutException

class HomeViewModel: ViewModel() {

    private var repository: Repository? = null
    var movies: MutableLiveData<Movies> = MutableLiveData()
    var fragmentInView: MutableLiveData<String> = MutableLiveData()
    var errorSearch: MutableLiveData<Int> = MutableLiveData()

    fun getMovies(name : String)
    {
        repository = RepositoryProvider.provideRepository()
        repository!!.getMovies("title="+name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {

            }
            .subscribe({
                    movies.value = it
            },{err ->
                errorSearch.value = 0
                when(err){
                    is SocketTimeoutException -> { }
                    is NoConnectivityException -> { }
                }
            })
    }
}