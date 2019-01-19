package pl.dmcs.pb.richchat.app.chat.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseFragment

class ChatListFragment : BaseFragment<ChatListPresenter>() {

    override fun onStart() {
        super.onStart()
        presenter.onStart()
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
