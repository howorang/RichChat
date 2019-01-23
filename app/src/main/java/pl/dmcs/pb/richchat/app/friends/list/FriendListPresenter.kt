package pl.dmcs.pb.richchat.app.friends.list

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_user_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.app.chat.view.ChatActivity
import pl.dmcs.pb.richchat.app.chat.view.USER_KEY
import pl.dmcs.pb.richchat.data.ChatRepository
import pl.dmcs.pb.richchat.data.entity.ChatHandle
import pl.dmcs.pb.richchat.data.entity.UserHandle
import pl.dmcs.pb.richchat.data.entity.getParticipantsHash
import javax.inject.Inject

class UserKeyNotProvided : Throwable()

class FriendListPresenter
@Inject
constructor(
    private val view: UserListFragment,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth,
    private val chatRepository: ChatRepository
) :  UserListPresenter(view, firebaseDatabase) {
    override fun bindOnUserClickListener(user: UserHandle): View.OnClickListener? = View.OnClickListener {
        startChatWithUser(user)
    }

    override fun getUsersPath(): String {
        val userId = firebaseAuth.currentUser!!.uid
        return "users/$userId/friends"
    }

    private fun startChatWithUser(user: UserHandle) {
        val currentUser = firebaseAuth.currentUser!!
        val chat = ChatHandle()
        chat.participants.addAll(arrayListOf(UserHandle(currentUser.uid, currentUser.displayName!!), user))
        chat.participantsHash = getParticipantsHash(chat.participants.map { it.userId })
        chatRepository.lookForExistingChat(firebaseAuth.currentUser!!.uid, user.userId, object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    val value = p0.children.first().getValue(ChatHandle::class.java)
                    startChat(value!!.chatId)
                } else {
                    startChat(chatRepository.createChat(chat))
                }
            }
        })
    }

    private fun startChat(chatId: String) {
        view.startActivity(ChatActivity.openChat(view.context!!, chatId))
    }


}