package tn.ocs.ocs_testtechnique.common.application

import android.app.Application
import android.content.Context
import android.widget.Toast
import tn.ocs.ocs_testtechnique.common.SharedPreference

class App: Application() {


    companion object{
        @get:Synchronized
        lateinit var instance: App
        private set
        lateinit var sharedPreference: SharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    override fun attachBaseContext(base: Context?) {
        sharedPreference = SharedPreference(base!!)
        super.attachBaseContext(base)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Toast.makeText(this,"Warning : Low on memroy !", Toast.LENGTH_LONG).show()
    }

    override fun onTerminate() {
        super.onTerminate()
        Toast.makeText(this,"App terminated",Toast.LENGTH_LONG).show()
    }

}