package pl.dmcs.pb.richchat.app.chat.view.conversation

import android.os.Bundle
import pl.dmcs.pb.richchat.app.BasePresenter
import pl.dmcs.pb.richchat.data.ChatRepository
import javax.inject.Inject

class ConversationPresenter
@Inject
constructor(chatRepository: ChatRepository) : BasePresenter() {

    fun onCreate(savedInstanceState: Bundle?) {

    }

}