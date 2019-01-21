package pl.dmcs.pb.richchat.app.friends.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseFragment

class UserListFragment : BaseFragment<UserListPresenter>() {

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val manager =  LinearLayoutManager(this.context)
        friend_list.layoutManager = manager
        friend_list.setHasFixedSize(true)
        presenter.onActivityCreated(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
