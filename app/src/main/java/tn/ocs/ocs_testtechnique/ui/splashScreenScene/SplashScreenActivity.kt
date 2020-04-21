package tn.ocs.ocs_testtechnique.ui.splashScreenScene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Nullable
import tn.ocs.ocs_testtechnique.ui.HomeScene.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
