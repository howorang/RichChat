package pl.dmcs.pb.richchat.app.chatlist

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_chat_element.view.*
import java.time.format.DateTimeFormatter

class ChatListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(chatDto: ChatDto) {
        itemView.username.text = chatDto.username
        itemView.last_message.text = chatDto.lastMessage
        itemView.last_message_timestamp.text = chatDto.lastMessageTimestamp.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
}