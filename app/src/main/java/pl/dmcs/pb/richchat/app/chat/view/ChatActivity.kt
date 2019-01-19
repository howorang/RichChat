package pl.dmcs.pb.richchat.app.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity

const val CHAT_ID_KEY = "chat_id"

class ChatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)
    }
    companion object {
        fun newIntent(context: Context, chatId : String): Intent {
            val intent = Intent(context, this::class.java)
            intent.putExtra(CHAT_ID_KEY, chatId)
            return intent
        }

    }
}
