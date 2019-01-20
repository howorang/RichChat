package pl.dmcs.pb.richchat.app.chat.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_chat_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.app.chat.view.ChatActivity
import pl.dmcs.pb.richchat.data.entity.ChatHandle
import javax.inject.Inject

class ChatListPresenter
@Inject
constructor(
    private val view: ChatListFragment,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
) : BasePresenter() {

    fun onStart() {
        initChatListAdapter()
    }

    private fun initChatListAdapter() {
        val userId = firebaseAuth.currentUser!!.uid
        val query = firebaseDatabase.reference.child("/users/$userId/chats/")
        val options = FirebaseRecyclerOptions
            .Builder<ChatHandle>()
            .setQuery(
                query
            ) { it.getValue<ChatHandle>(ChatHandle::class.java)!! }
            .build()

        val adapter = object : FirebaseRecyclerAdapter<ChatHandle, ChatListViewHolder>(options) {
            override fun onBindViewHolder(holder: ChatListViewHolder, position: Int, model: ChatHandle) {
                holder.bind(model)
                bindOnClickListener(holder.itemView, model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
                val viewV = LayoutInflater.from(view.context)
                    .inflate(R.layout.fragment_chat_element, parent, false)
                return ChatListViewHolder(viewV)
            }
        }
        view.recycler.adapter = adapter
    }

    fun bindOnClickListener(view: View, model: ChatHandle) {
        view.setOnClickListener {
            view.context.startActivity(ChatActivity.startChatWithUser(view.context, model.chatId))
        }
    }
}