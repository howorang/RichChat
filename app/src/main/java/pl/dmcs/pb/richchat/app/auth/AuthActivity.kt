package pl.dmcs.pb.richchat.app.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.main.MainActivity

class AuthActivity : BaseActivity() {

    val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setSupportActionBar(toolbar)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
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
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .putExtra("aut_token", response?.idpToken)
                )
                finish()
            }
            else -> handleFailure(requestCode, response)
        }
    }

    private fun handleFailure(requestCode: Int, response: IdpResponse?) {

    }
}
