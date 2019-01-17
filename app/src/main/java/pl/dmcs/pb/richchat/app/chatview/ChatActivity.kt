package pl.dmcs.pb.richchat.app.chatview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity

class ChatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)
    }

    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context, user: User): Intent {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(INTENT_USER_ID, user.id)
            return intent
        }
    }
}
