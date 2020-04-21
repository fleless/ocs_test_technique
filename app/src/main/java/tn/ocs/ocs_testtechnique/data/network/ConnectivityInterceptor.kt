package tn.ocs.ocs_testtechnique.data.network

import android.content.Context
import com.pwingo.kdhaya.network.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import tn.ocs.ocs_testtechnique.common.extensions.NoConnectivityException
import java.io.IOException

class ConnectivityInterceptor (private var context: Context) : Interceptor{




    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isOnline(context)) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}