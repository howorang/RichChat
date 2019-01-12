package pl.dmcs.pb.richchat.app.chatlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.dmcs.pb.richchat.R

class ChatListRecyclerViewAdapter(
) : RecyclerView.Adapter<ChatListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_chat_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 1

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    }
}
