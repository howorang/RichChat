package pl.dmcs.pb.richchat.app.friends.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_friend_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import javax.inject.Inject

class FriendListPresenter
@Inject
constructor(
    private val view: FriendListActivity,
    private val firebaseDatabase: FirebaseDatabase
) : BasePresenter() {

    data class UserLabel(
        var userId: String,
        var username: String
    )

    class UserViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var username : String
    }

    fun onCreate(savedInstanceState: Bundle?) {
        initChatListAdapter()
    }

    private fun initChatListAdapter() {
        val query = firebaseDatabase.reference.child("/users/")
        val options = FirebaseRecyclerOptions
            .Builder<UserLabel>()
            .setQuery(
                query
            ) { it.getValue(UserLabel::class.java)!! }
            .build()

        val adapter = object : FirebaseRecyclerAdapter<UserLabel, UserViewHolder>(options) {

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: UserLabel) {
                holder.username = model.username
                bindOnClickListener(holder.itemView, model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                val viewV = LayoutInflater.from(view)
                    .inflate(R.layout.fragment_chat_element, parent, false)
                return UserViewHolder(viewV)
            }

            private fun bindOnClickListener(itemView: View, model: UserLabel) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        view.friend_list.adapter = adapter
    }

}