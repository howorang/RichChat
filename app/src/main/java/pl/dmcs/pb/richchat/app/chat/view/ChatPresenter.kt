package pl.dmcs.pb.richchat.app.chat.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chat_view.*
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.data.ChatRepository
import pl.dmcs.pb.richchat.data.entity.ChatHandle
import pl.dmcs.pb.richchat.data.entity.Message
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
        bindSendButtons()
    }

    private fun bindSendButtons() {
        view.message_edit_text.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    sendMessage()
                    true
                }
                else -> false
            }
        }
        view.send_button.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val currentUser = firebaseAuth.currentUser!!
        val message = Message(
            text = view.message_edit_text.text.toString(), senderDisplayName = currentUser.displayName!!,
            senderId = currentUser.uid
        )
        chatRepository.sendMessage(chatId, message)
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