package pl.dmcs.pb.richchat.app.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.iid.FirebaseInstanceId
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.main.MainActivity
import pl.dmcs.pb.richchat.data.UserRepository
import pl.dmcs.pb.richchat.data.entity.User
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userRepository: UserRepository

    val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setSupportActionBar(toolbar)

        if (firebaseAuth.currentUser != null) {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        } else {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(
                        arrayListOf(
                            AuthUI.IdpConfig.EmailBuilder().build()
                        )
                    )
                    .build(),
                RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (RC_SIGN_IN == requestCode) {
            onSignInResult(data, resultCode, requestCode)
        }
    }

    private fun onSignInResult(data: Intent?, resultCode: Int, requestCode: Int) {
        val response = IdpResponse.fromResultIntent(data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                addToDb()
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .putExtra("aut_token", response?.idpToken)
                )
                finish()
            }
            else -> handleFailure(requestCode, response)
        }
    }

    private fun addToDb() {
        val currentUser: FirebaseUser = firebaseAuth.currentUser!!
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener { id ->
            val user = User(currentUser.uid, id.token, currentUser.displayName!!, null, null)
            userRepository.createUser(user)
        }

    }

    private fun handleFailure(requestCode: Int, response: IdpResponse?) {

    }
}
