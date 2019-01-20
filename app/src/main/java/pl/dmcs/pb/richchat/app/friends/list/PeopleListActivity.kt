package pl.dmcs.pb.richchat.app.friends.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_people_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import javax.inject.Inject

class PeopleListActivity : BaseActivity() {

    @Inject
    lateinit var presenter : PeopleListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_list)
        val manager =  LinearLayoutManager(this)
        friend_list.layoutManager = manager
        friend_list.setHasFixedSize(true)
        presenter.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}
