package pl.dmcs.pb.richchat.app

import android.app.FragmentManager
import android.content.Context

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.dagger.ActivityScope

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    internal abstract fun bindContext(activity: BaseActivity): Context

    @Module
    companion object {
        @ActivityScope
        @Provides
        @JvmStatic
        fun provideFragmentManager(activity: BaseActivity): FragmentManager {
            return activity.fragmentManager
        }

        @ActivityScope
        @Provides
        @JvmStatic
        fun provideSupportFragmentManager(baseActivity: BaseActivity): android.support.v4.app.FragmentManager {
            return baseActivity.supportFragmentManager

        }
    }
}