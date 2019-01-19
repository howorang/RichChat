package pl.dmcs.pb.richchat.app.chat.view

import android.os.Bundle
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.data.ChatRepository
import pl.dmcs.pb.richchat.data.entity.ChatHandle
import javax.inject.Inject

class NoParamertersPassed() : Throwable()

class ChatPresenter
@Inject
constructor(
    private val view: ChatActivity,
    private val chatRepository: ChatRepository
) : BasePresenter() {

    private lateinit var chatId: String

    fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState!!
        chatId =
                when {
                    savedInstanceState.containsKey(CHAT_ID_KEY) -> savedInstanceState.getString(CHAT_ID_KEY)
                    savedInstanceState.containsKey(USER_ID_KEY) -> startChatWithUser(
                        savedInstanceState.getString(
                            USER_ID_KEY
                        )
                    )
                    else -> throw  NoParamertersPassed()
                }
    }

    private fun startChatWithUser(userId: String): String {
        val chat = ChatHandle(userId)
        return chatRepository.createChat(chat)
    }

}