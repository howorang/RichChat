package pl.dmcs.pb.richchat.app

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }
}