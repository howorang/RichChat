package pl.dmcs.pb.richchat.app.chatlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListFragment : BaseFragment<ChatListPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)
        return view
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
