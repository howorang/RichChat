package pl.dmcs.pb.richchat.app.friends.list

import android.os.Bundle
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import javax.inject.Inject

class FriendListActivity : BaseActivity() {

    @Inject
    lateinit var presenter : FriendListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)
        presenter.onCreate(savedInstanceState)
    }
}
