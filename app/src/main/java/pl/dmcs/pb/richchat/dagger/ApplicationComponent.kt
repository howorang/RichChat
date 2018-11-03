package pl.dmcs.pb.richchat.dagger

import javax.inject.Singleton
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

/**
 * Created by Piotr Borczyk on 20.12.2017.
 */

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()

}