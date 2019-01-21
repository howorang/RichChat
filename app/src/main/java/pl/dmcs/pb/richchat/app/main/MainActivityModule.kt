package pl.dmcs.pb.richchat.app.main

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.BaseActivityModule
import pl.dmcs.pb.richchat.app.Presenter
import pl.dmcs.pb.richchat.app.chat.list.ChatListFragment
import pl.dmcs.pb.richchat.app.chat.list.ChatListFragmentModule
import pl.dmcs.pb.richchat.app.friends.list.FriendListPresenter
import pl.dmcs.pb.richchat.app.friends.list.UserListFragment
import pl.dmcs.pb.richchat.app.friends.list.UserListFragmentModule
import pl.dmcs.pb.richchat.app.friends.list.UserListPresenter
import pl.dmcs.pb.richchat.dagger.ActivityScope
import pl.dmcs.pb.richchat.dagger.FragmentScope

/**
 * Created by howor on 24.12.2017.
 */

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindBaseActivity(mainActivity: MainActivity): BaseActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [ChatListFragmentModule::class])
    abstract fun bindChatListFragment() : ChatListFragment


    @FragmentScope
    @ContributesAndroidInjector(modules = [UserListFragmentModule::class])
    abstract fun bindUserListFragment() : UserListFragment


    @Binds
    @FragmentScope
    internal abstract fun bindListPresenterInjector(presenter: FriendListPresenter): UserListPresenter
}