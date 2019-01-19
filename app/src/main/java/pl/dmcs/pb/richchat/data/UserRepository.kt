package pl.dmcs.pb.richchat.data

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pl.dmcs.pb.richchat.data.entity.User

class UserRepository(val database: FirebaseDatabase) {

    fun createUser(user : User) : String {
        val reference = database.reference
        val newChildRef = reference.child("users").push()
        user.userId = newChildRef.key!!
        newChildRef.setValue(user)
        return user.userId
    }

    fun getUserById(id : String, listener: ValueEventListener)  {
        val ref = database.reference
        ref.child("users")
            .child(id)
            .addListenerForSingleValueEvent(listener)
    }
}