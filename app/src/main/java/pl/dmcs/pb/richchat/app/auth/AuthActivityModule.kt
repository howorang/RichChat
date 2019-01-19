package pl.dmcs.pb.richchat.app.auth

import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerAppCompatActivity
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.BaseActivityModule
import pl.dmcs.pb.richchat.app.Presenter
import pl.dmcs.pb.richchat.dagger.ActivityScope

/**
 * Created by howor on 24.12.2017.
 */

@Module(includes = [BaseActivityModule::class])
abstract class AuthActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindBaseActivity(mainActivity: AuthActivity): DaggerAppCompatActivity
}