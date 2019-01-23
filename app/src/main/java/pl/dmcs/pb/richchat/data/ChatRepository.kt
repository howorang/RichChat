package pl.dmcs.pb.richchat.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pl.dmcs.pb.richchat.data.entity.*
import java.util.*

class ChatRepository(val database: FirebaseDatabase) {

    fun createChat(chatHandle: ChatHandle): String {
        val chatRef = database.reference.child("/chat_messages").push()
        chatHandle.chatId = chatRef.key!!
        val chatMessages = ChatMessages(
            chatId = chatHandle.chatId,
            participants = chatHandle.participants.map { userHandle -> userHandle.userId }.toMutableList()
        )
        chatRef.setValue(chatMessages)
        chatHandle.participants.forEach {
            val userChatRef = database.reference.child("/users/${it.userId}/chats/${chatHandle.chatId}")
            userChatRef.setValue(chatHandle)
        }
        return chatHandle.chatId
    }

    fun sendMessage(chatId: String, message: Message) {
        message.messageTimestamp = messageDateFormat.format(Date())
        val reference = database.reference
        val chatRef = reference.child("/chat_messages/$chatId/messages")
        val messageRef = chatRef.push()
        messageRef.setValue(message)
    }

    fun lookForExistingChat(currentUserId: String, userId: String, valueEventListener: ValueEventListener) {
        val particpantsHash = getParticipantsHash(arrayListOf(currentUserId, userId))
        database.getReference("users/$currentUserId/chats")
            .orderByChild("participantsHash")
            .equalTo(particpantsHash)
            .limitToFirst(1)
            .addListenerForSingleValueEvent(valueEventListener)
    }
}