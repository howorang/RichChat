package pl.dmcs.pb.richchat.app.chat.view

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.data.ChatRepository
import pl.dmcs.pb.richchat.data.entity.ChatHandle
import javax.inject.Inject

class NoParamertersPassed : Throwable()

class ChatPresenter
@Inject
constructor(
    private val view: ChatActivity,
    private val chatRepository: ChatRepository,
    private val firebaseAuth: FirebaseAuth
) : BasePresenter() {

    private lateinit var chatId: String

    fun onCreate(savedInstanceState: Bundle?) {
        initChatId()
        view.startConversationFragment(chatId)
    }

    private fun initChatId() {
        val extras = view.intent.extras
        chatId =
                when {
                    extras.containsKey(CHAT_ID_KEY) -> extras.getString(CHAT_ID_KEY)
                    extras.containsKey(USER_ID_KEY) -> startChatWithUser(
                        extras.getString(USER_ID_KEY)
                    )
                    else -> throw  NoParamertersPassed()
                }
    }

    private fun startChatWithUser(userId: String): String {
        val currentUser = firebaseAuth.currentUser!!
        val chat = ChatHandle()
        chat.participants.addAll(arrayListOf(currentUser.uid, userId))
        return chatRepository.createChat(chat)
    }

}