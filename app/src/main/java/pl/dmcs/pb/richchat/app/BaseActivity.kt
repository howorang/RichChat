package pl.dmcs.pb.richchat.app


import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.widget.Toast
import butterknife.ButterKnife
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}