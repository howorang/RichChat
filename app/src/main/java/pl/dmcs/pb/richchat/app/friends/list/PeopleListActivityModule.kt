package pl.dmcs.pb.richchat.app.friends.list

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.BaseActivityModule
import pl.dmcs.pb.richchat.app.Presenter
import pl.dmcs.pb.richchat.app.chat.list.ChatListFragment
import pl.dmcs.pb.richchat.app.chat.list.ChatListFragmentModule
import pl.dmcs.pb.richchat.dagger.ActivityScope
import pl.dmcs.pb.richchat.dagger.FragmentScope

/**
 * Created by howor on 24.12.2017.
 */

@Module(includes = [BaseActivityModule::class])
abstract class PeopleListActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindBaseActivity(mainActivity: PeopleListActivity): BaseActivity
}