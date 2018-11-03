package pl.dmcs.pb.richchat.app.main

import dagger.Binds
import dagger.Module
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.BaseActivityModule
import pl.dmcs.pb.richchat.dagger.ActivityScope

/**
 * Created by howor on 24.12.2017.
 */

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindBaseActivity(mainActivity: MainActivity): BaseActivity
}