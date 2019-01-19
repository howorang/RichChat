package pl.dmcs.pb.richchat.dagger.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import pl.dmcs.pb.richchat.data.ChatRepository
import pl.dmcs.pb.richchat.data.UserRepository
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideFirebaseDatabase () : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepo(database: FirebaseDatabase) : UserRepository = UserRepository(database)

    @Provides
    @Singleton
    fun provideChatRepo(database: FirebaseDatabase) : ChatRepository = ChatRepository(database)


}