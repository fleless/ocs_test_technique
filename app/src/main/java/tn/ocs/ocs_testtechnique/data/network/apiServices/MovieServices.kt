package tn.ocs.ocs_testtechnique.data.network.apiServices

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tn.ocs.ocs_testtechnique.data.model.Movies

interface MovieServices {

    @GET("apps/v2/contents")
    fun getMoviesSearch(@Query("search") name: String?): Observable<Movies>

}