package pl.dmcs.pb.richchat.app

import dagger.android.support.DaggerApplication
import pl.dmcs.pb.richchat.dagger.DaggerApplicationComponent

class RichChatApp : DaggerApplication() {
    override fun applicationInjector() = DaggerApplicationComponent.builder().create(this)

}