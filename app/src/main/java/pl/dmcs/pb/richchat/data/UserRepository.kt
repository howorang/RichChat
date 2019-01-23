package pl.dmcs.pb.richchat.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pl.dmcs.pb.richchat.data.entity.User
import pl.dmcs.pb.richchat.data.entity.UserHandle

class UserRepository(private val database: FirebaseDatabase) {

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

    fun addFriend(currentUserHandle: UserHandle, friend: UserHandle) {
        val ref = database.reference
        val myRef = ref.child("users/${currentUserHandle.userId}/friends").push()
        myRef.setValue(friend)
        val firendRef = ref.child("users/${friend.userId}/friends").push()
        firendRef.setValue(currentUserHandle)
    }
}