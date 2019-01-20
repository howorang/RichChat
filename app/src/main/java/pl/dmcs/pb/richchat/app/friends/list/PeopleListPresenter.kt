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
import kotlinx.android.synthetic.main.activity_people_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.app.chat.view.ChatActivity
import javax.inject.Inject

class PeopleListPresenter
@Inject
constructor(
    private val view: PeopleListActivity,
    private val firebaseDatabase: FirebaseDatabase
) : BasePresenter() {

    data class UserLabel(
        var userId: String = "",
        var username: String = ""
    )

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    fun onCreate(savedInstanceState: Bundle?) {
        initChatListAdapter()
    }

    private fun initChatListAdapter() {
        val query = firebaseDatabase.reference.child("users/")
        val options = FirebaseRecyclerOptions
            .Builder<UserLabel>()
            .setQuery(
                query
            ) { it.getValue(UserLabel::class.java)!! }
            .build()

        val adapter = object : FirebaseRecyclerAdapter<UserLabel, MyViewHolder>(options) {
            override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: UserLabel) {
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

            private fun bindOnClickListener(itemView: View, model: UserLabel) {
                itemView.setOnClickListener{
                    val alertDialog: AlertDialog = view?.let {
                        val builder = AlertDialog.Builder(it)
                        builder.apply {
                            setPositiveButton(
                                R.string.ok
                            ) { dialog, id ->
                                startChat(model.userId)
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
        adapter.startListening()
    }

    private fun startChat(userId: String) {
        ChatActivity.startChatWithUser(view, userId)
    }

}