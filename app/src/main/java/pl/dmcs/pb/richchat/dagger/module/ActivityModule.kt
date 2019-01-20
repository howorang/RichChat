package pl.dmcs.pb.richchat.dagger.module

import dagger.Module

import dagger.android.ContributesAndroidInjector
import pl.dmcs.pb.richchat.app.auth.AuthActivity
import pl.dmcs.pb.richchat.app.auth.AuthActivityModule
import pl.dmcs.pb.richchat.app.chat.view.ChatActivity
import pl.dmcs.pb.richchat.app.chat.view.ChatActivityModule
import pl.dmcs.pb.richchat.app.friends.list.PeopleListActivity
import pl.dmcs.pb.richchat.app.main.MainActivity
import pl.dmcs.pb.richchat.app.main.MainActivityModule
import pl.dmcs.pb.richchat.dagger.ActivityScope

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector() : MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [AuthActivityModule::class])
    abstract fun authActivityInjector() : AuthActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [AuthActivityModule::class])
    abstract fun peopleActivityInjector() : PeopleListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ChatActivityModule::class])
    abstract fun chatActivityInjector() : ChatActivity
}