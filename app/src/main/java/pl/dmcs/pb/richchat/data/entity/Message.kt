package pl.dmcs.pb.richchat.data.entity

import java.text.SimpleDateFormat
import java.util.*

val messageDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

data class Message(
    var senderId: String = "",
    var text: String = "",
    var attachment: Attachment? = null,
    var senderDisplayName: String = "",
    var messageTimestamp: String = ""
)