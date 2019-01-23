package pl.dmcs.pb.richchat.data.entity

import java.util.*

data class ChatHandle(
    var chatId: String = "",
    var participants: MutableList<UserHandle> = mutableListOf(),
    var participantsHash: String = ""
)

fun getParticipantsHash(participants: List<String>): String {
    return participants.sortedBy { it }
        .reduce { sum, element -> "$sum$element" }
        .hashCode().toString()
}