package pl.dmcs.pb.richchat.data

import com.google.firebase.database.FirebaseDatabase
import pl.dmcs.pb.richchat.data.entity.ChatHandle
import pl.dmcs.pb.richchat.data.entity.ChatMessages
import pl.dmcs.pb.richchat.data.entity.Message

class ChatRepository(val database: FirebaseDatabase) {

    fun createChat(chatHandle: ChatHandle): String {
        val chatRef = database.reference.child("chat_messages").push()
        chatHandle.chatId = chatRef.key!!
        val chatMessages = ChatMessages(chatId = chatHandle.chatId)
        chatRef.setValue(chatMessages)
        chatHandle.participants.forEach {
            val userChatRef = database.reference.child("/users/$it/chats/${chatHandle.chatId}")
            userChatRef.setValue(chatHandle)
        }
        return chatHandle.chatId
    }

    fun sendMessage(chatId : String, message: Message) {
        val reference = database.reference
        val chatRef = reference.child("/chats/$chatId/messages")
        val messageRef = chatRef.push()
        messageRef.setValue(message)
    }
}