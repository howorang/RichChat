package pl.dmcs.pb.richchat.app.chatlist

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_chat_element.view.*
import pl.dmcs.pb.richchat.data.entity.ChatLabel
import java.time.format.DateTimeFormatter

class ChatListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(chatDto: ChatLabel) {
        itemView.username.text = chatDto.participants.reduce{sum, element -> "$sum,$element" }
    }
}