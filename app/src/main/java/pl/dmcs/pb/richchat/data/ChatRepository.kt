package pl.dmcs.pb.richchat.data

import com.google.firebase.database.FirebaseDatabase
import pl.dmcs.pb.richchat.data.entity.ChatLabel
import pl.dmcs.pb.richchat.data.entity.ChatMessages
import pl.dmcs.pb.richchat.data.entity.Message
import javax.inject.Inject

class ChatRepository {
    @Inject
    lateinit var database: FirebaseDatabase

    fun createChat(chatLabel: ChatLabel): String {
        val chatRef = database.reference.child("chat_messages").push()
        chatLabel.chatId = chatRef.key!!
        val chatMessages = ChatMessages(chatId = chatLabel.chatId)
        chatRef.setValue(chatMessages)
        chatLabel.participants.forEach {
            val userChatRef = database.reference.child("/users/$it/chats/${chatLabel.chatId}")
            userChatRef.setValue(chatLabel)
        }
        return chatLabel.chatId
    }

    fun sendMessage(chatId : String, message: Message) {
        val reference = database.reference
        val chatRef = reference.child("/chats/$chatId/messages")
        val messageRef = chatRef.push()
        messageRef.setValue(message)
    }


}