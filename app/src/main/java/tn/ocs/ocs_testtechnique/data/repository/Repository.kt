package tn.ocs.ocs_testtechnique.data.repository

import io.reactivex.Observable
import tn.ocs.ocs_testtechnique.data.model.Movies
import tn.ocs.ocs_testtechnique.data.network.RestClient

class Repository (private val apiService: RestClient) {

    fun getMovies(name: String): Observable<Movies> {
        return apiService.create().getMoviesSearch(name)
    }

}