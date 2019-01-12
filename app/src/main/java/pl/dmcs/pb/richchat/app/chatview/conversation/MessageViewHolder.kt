package pl.dmcs.pb.richchat.app.chatview.conversation

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.layout_message.view.*
import java.time.format.DateTimeFormatter

class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(message : Message) {
        itemView.message_content.text = message.content
        itemView.message_name.text = message.username
        itemView.message_timestamp.text = message.timestamp.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
}
