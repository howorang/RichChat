package pl.dmcs.pb.richchat.data

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pl.dmcs.pb.richchat.data.entity.User
import pl.dmcs.pb.richchat.data.entity.UserHandle

class UserRepository(val database: FirebaseDatabase) {

    fun createUser(user : User) : String {
        val reference = database.reference
        val newChildRef = reference.child("users/${user.userId}")
        newChildRef.setValue(user)
        return user.userId
    }

    fun getUserById(id : String, listener: ValueEventListener)  {
        val ref = database.reference
        ref.child("users")
            .child(id)
            .addListenerForSingleValueEvent(listener)

    }

    fun addFriend(userId: String, friend: UserHandle) {
        val ref = database.reference
        val newFriendRef = ref.child("users/$userId/friends").push()
        newFriendRef.setValue(friend)
    }
}