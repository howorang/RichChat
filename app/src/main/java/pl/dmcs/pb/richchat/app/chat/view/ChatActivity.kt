package pl.dmcs.pb.richchat.app.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import javax.inject.Inject

const val CHAT_ID_KEY = "chat_id"
const val USER_ID_KEY = "user_id"

class ChatActivity
@Inject
constructor(private val presenter: ChatPresenter) : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)
        presenter.onCreate(savedInstanceState)
    }

    companion object {
        fun openChat(context: Context, chatId: String): Intent {
            val intent = Intent(context, this::class.java)
            intent.putExtra(CHAT_ID_KEY, chatId)
            return intent
        }

        fun startChatWithUser(context: Context, userId: String): Intent {
            val intent = Intent(context, this::class.java)
            intent.putExtra(USER_ID_KEY, userId)
            return intent
        }

    }
}
