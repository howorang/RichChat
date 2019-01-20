package pl.dmcs.pb.richchat.app.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.chat.view.conversation.ConversationFragment
import pl.dmcs.pb.richchat.data.entity.UserHandle
import javax.inject.Inject

const val CHAT_ID_KEY = "chat_id"
const val USER_KEY = "user_id"

class ChatActivity
@Inject
constructor() : BaseActivity() {

    @Inject
    lateinit var presenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)
        presenter.onCreate(savedInstanceState)
    }

    fun startConversationFragment(chatId: String) {
        replaceFragment(R.id.conversation_fragment_container, ConversationFragment.newInstance(chatId))
    }

    companion object {
        fun openChat(context: Context, chatId: String): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(CHAT_ID_KEY, chatId)
            return intent
        }

        fun startChatWithUser(context: Context, user: UserHandle): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(USER_KEY, user)
            return intent
        }

    }
}
