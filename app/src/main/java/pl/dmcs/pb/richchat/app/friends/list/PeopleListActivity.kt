package pl.dmcs.pb.richchat.app.friends.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_people_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import javax.inject.Inject

class PeopleListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_list)
        replaceFragment(R.id.fragment_container, UserListFragment.newInstance())
    }

}
