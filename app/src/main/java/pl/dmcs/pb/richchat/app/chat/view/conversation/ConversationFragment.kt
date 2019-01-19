package pl.dmcs.pb.richchat.app.chat.view.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseFragment

class ConversationFragment : BaseFragment<ConversationPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chatview, container, false)
        return view
    }

}
