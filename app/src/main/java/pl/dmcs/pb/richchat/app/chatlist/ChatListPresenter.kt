package pl.dmcs.pb.richchat.app.chatlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.data.entity.ChatLabel
import javax.inject.Inject

class ChatListPresenter
@Inject
constructor(
    private val fragment: ChatListFragment,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth : FirebaseAuth
) : BasePresenter() {

    fun onCreate(savedInstanceState: Bundle?) {
        initChatListAdapter()
    }

    private fun initChatListAdapter() {
        val userId = firebaseAuth.currentUser!!.uid
        val query = firebaseDatabase.reference.child("/users/$userId/chats/")
        val options = FirebaseRecyclerOptions
            .Builder<ChatLabel>()
            .setQuery(query
            ) { it.getValue<ChatLabel>(ChatLabel::class.java)!! }
            .build()

        val adapter = object : FirebaseRecyclerAdapter<ChatLabel, ChatListViewHolder>(options) {
            override fun onBindViewHolder(holder: ChatListViewHolder, position: Int, model: ChatLabel) {
                holder.bind(model)
                bindOnClickListener(holder.itemView, model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
                val view = LayoutInflater.from(fragment.context)
                    .inflate(R.layout.fragment_chat_element, parent, false)
                return ChatListViewHolder(view)
            }

        }
    }

    fun bindOnClickListener(view : View, model : ChatLabel) {
        view.setOnClickListener { View.OnClickListener { TODO() } }
    }
}