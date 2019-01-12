package pl.dmcs.pb.richchat.data

import com.google.firebase.database.FirebaseDatabase
import pl.dmcs.pb.richchat.data.entity.Chat
import pl.dmcs.pb.richchat.data.entity.Message
import javax.inject.Inject

class ChatRepository {
    @Inject
    lateinit var database: FirebaseDatabase

    fun createChat(chat: Chat, participants: List<String>): String {
        val chatRef = database.reference.child("chats").push()
        chat.chatId = chatRef.key!!
        chatRef.setValue(chat)
        participants.forEach {
            val userChatRef = database.reference.child("/users/$it/chats/${chat.chatId}")
            userChatRef.setValue(true)
        }
        return chat.chatId
    }

    fun sendMessage(chat: Chat, message: Message) {
        val reference = database.reference
        val chatRef = reference.child("/chats/${chat.chatId}/messages")
        val messageRef = chatRef.push()
        messageRef.setValue(message)
    }
}