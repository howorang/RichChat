package pl.dmcs.pb.richchat.app.chat.view

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.BaseActivityModule
import pl.dmcs.pb.richchat.app.Presenter
import pl.dmcs.pb.richchat.app.chat.view.conversation.ConversationFragment
import pl.dmcs.pb.richchat.app.chat.view.conversation.ConversationFragmentModule
import pl.dmcs.pb.richchat.dagger.ActivityScope
import pl.dmcs.pb.richchat.dagger.FragmentScope

/**
 * Created by howor on 24.12.2017.
 */

@Module(includes = [BaseActivityModule::class])
abstract class ChatActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindBaseActivity(chatActivity: ChatActivity): BaseActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [ConversationFragmentModule::class])
    abstract fun bindConversationFragment() : ConversationFragment
}