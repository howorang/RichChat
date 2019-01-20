package pl.dmcs.pb.richchat.notification

import com.google.firebase.messaging.FirebaseMessagingService

class MessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
      //  sendRegistrationToServer(token)
    }

}