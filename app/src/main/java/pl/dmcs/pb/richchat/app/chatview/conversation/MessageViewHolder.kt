package pl.dmcs.pb.richchat.app.chatview.conversation

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.layout_message.view.*
import pl.dmcs.pb.richchat.data.entity.Message
import java.time.format.DateTimeFormatter

class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(message : Message) {
        itemView.message_content.text = message.text
        itemView.message_name.text = message.senderDisplayName
        itemView.message_timestamp.text = message.messageTimestamp.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
}
