package pl.dmcs.pb.richchat.app.chat.view.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_chatview.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.app.chat.view.CHAT_ID_KEY
import pl.dmcs.pb.richchat.data.entity.Message
import javax.inject.Inject

class ViewHolderTypeException : Throwable()

class ConversationPresenter
@Inject
constructor(
    private val view: ConversationFragment,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
) : BasePresenter() {

    val OUT_VH_TYPE = 1
    val IN_VH_TYPE = 2

    private lateinit var adapter: FirebaseRecyclerAdapter<Message, MessageViewHolder>
    private lateinit var chatId: String

    fun onCreate(savedInstanceState: Bundle?) {
        val arguments = view.arguments!!
        chatId = arguments.getString(CHAT_ID_KEY)!!

    }

    fun onResume() {
        if (!::adapter.isInitialized) {
            initMessageAdapter()
        }
        adapter.startListening()
    }

    fun onPause() {
        adapter.stopListening()
    }

    private fun initMessageAdapter() {
        val query = firebaseDatabase.reference.child("chat_messages/$chatId/messages/")
        val options = FirebaseRecyclerOptions
            .Builder<Message>()
            .setQuery(
                query
            ) { it.getValue<Message>(Message::class.java)!! }
            .build()
        adapter = object : FirebaseRecyclerAdapter<Message, MessageViewHolder>(options) {
            override fun onBindViewHolder(holder: MessageViewHolder, position: Int, model: Message) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
                val viewV = when (viewType) {
                    IN_VH_TYPE -> LayoutInflater.from(view.context)
                        .inflate(R.layout.fragment_message_received, parent, false)
                    OUT_VH_TYPE -> LayoutInflater.from(view.context)
                        .inflate(R.layout.fragment_message_sent, parent, false)
                    else -> throw ViewHolderTypeException()
                }
                return MessageViewHolder(viewV)
            }

            override fun getItemViewType(position: Int): Int {
                val message = getItem(position)
                return when (message.senderId == firebaseAuth.currentUser!!.uid) {
                    true -> OUT_VH_TYPE
                    false -> IN_VH_TYPE
                }
            }
        }
        view.list.adapter = adapter
    }

}