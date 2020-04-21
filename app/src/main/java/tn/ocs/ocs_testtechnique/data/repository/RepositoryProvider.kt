package tn.ocs.ocs_testtechnique.data.repository

import tn.ocs.ocs_testtechnique.data.network.RestClient

object RepositoryProvider {

    fun provideRepository(): Repository{

        return Repository(RestClient)

    }

}