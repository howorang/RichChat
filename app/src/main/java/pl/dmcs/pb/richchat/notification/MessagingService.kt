package pl.dmcs.pb.richchat.notification

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService

class MessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            sendTokenToServer(firebaseAuth.currentUser!!, token)
        }
    }

    private fun sendTokenToServer(currentUser: FirebaseUser, token: String?) {
        val firebaseDb = FirebaseDatabase.getInstance()
        val uid = currentUser.uid
        firebaseDb.reference.child("users/$uid/pushKey").setValue(token)

    }

}