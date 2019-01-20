package pl.dmcs.pb.richchat.app.chat.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_chat_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseFragment

class ChatListFragment : BaseFragment<ChatListPresenter>() {

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        val manager =  LinearLayoutManager(this.context)
        recycler.layoutManager = manager
        recycler.setHasFixedSize(true)
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ChatListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
