package pl.dmcs.pb.richchat.app

import android.widget.Toast
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<P : Presenter> : DaggerFragment() {

    @Inject
    protected lateinit var presenter: P;

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}