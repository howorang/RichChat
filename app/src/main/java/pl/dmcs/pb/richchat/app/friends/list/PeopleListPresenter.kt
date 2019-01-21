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
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_list.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.app.chat.view.ChatActivity
import pl.dmcs.pb.richchat.data.UserRepository
import pl.dmcs.pb.richchat.data.entity.UserHandle
import javax.inject.Inject

class PeopleListPresenter
@Inject
constructor(
    private val userRepository: UserRepository,
    private val firebaseAuth : FirebaseAuth,
    private val view: UserListFragment,
    private val firebaseDatabase: FirebaseDatabase
) : UserListPresenter(view, firebaseDatabase) {
    override fun bindOnUserClickListener(model: UserHandle): View.OnClickListener? = View.OnClickListener {
        val alertDialog: AlertDialog = view?.let {
            val builder = AlertDialog.Builder(it.context)
            builder.apply {
                setPositiveButton(
                    R.string.ok
                ) { dialog, id ->
                    addToFriends(model)
                }
                setNegativeButton(R.string.cancel) { _, _ -> Unit }
            }
            builder.create()
        }
        alertDialog.show()
    }

    private fun addToFriends(model: UserHandle) {
        userRepository.addFriend(firebaseAuth.currentUser!!.uid, model)
    }

    override fun getUsersPath(): String = "/users"

}