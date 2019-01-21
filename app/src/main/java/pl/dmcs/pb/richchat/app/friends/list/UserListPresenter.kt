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
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.app.chat.view.ChatActivity
import pl.dmcs.pb.richchat.data.entity.UserHandle
import javax.inject.Inject

abstract class  UserListPresenter
constructor(
    private val view: UserListFragment,
    private val firebaseDatabase: FirebaseDatabase
) : BasePresenter()  {
    private lateinit var adapter: FirebaseRecyclerAdapter<UserHandle, MyViewHolder>

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    fun onActivityCreated(savedInstanceState: Bundle?) {
        initUserListAdapter()
    }

    fun onResume() {
        adapter.startListening()
    }

    fun onPause() {
        adapter.stopListening()
    }

    private fun initUserListAdapter() {
        val query = firebaseDatabase.reference.child("users/")
        val options = FirebaseRecyclerOptions
            .Builder<UserHandle>()
            .setQuery(
                query
            ) { it.getValue(UserHandle::class.java)!! }
            .build()

        adapter = object : FirebaseRecyclerAdapter<UserHandle, MyViewHolder>(options) {
            override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: UserHandle) {
                holder.textView.text = model.username
                bindOnClickListener(holder.itemView, model)
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): MyViewHolder {
                val textView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.simple_textview, parent, false) as TextView
                return MyViewHolder(textView)
            }

            private fun bindOnClickListener(itemView: View, model: UserHandle) {
                itemView.setOnClickListener{
                    val alertDialog: AlertDialog = view?.let {
                        val builder = AlertDialog.Builder(it.context)
                        builder.apply {
                            setPositiveButton(
                                R.string.ok
                            ) { dialog, id ->
                                startChat(model)
                            }
                            setNegativeButton(R.string.cancel) { _, _ -> Unit }
                        }
                        builder.create()
                    }
                    alertDialog.show()
                }
            }
        }
        view.friend_list.adapter = adapter
    }

    private fun startChat(userId: UserHandle) {
        view.startActivity(ChatActivity.startChatWithUser(view.context!!, userId))
    }

}