package pl.dmcs.pb.richchat.app.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.chat.list.ChatListFragment
import pl.dmcs.pb.richchat.app.friends.list.PeopleListActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_chats -> {
                    replaceFragment(R.id.fragment_container, ChatListFragment.newInstance(1))
                    true
                }
                R.id.nav_public -> true
                R.id.nav_people -> true
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.people_option -> {
                startActivity(Intent(this, PeopleListActivity::class.java))
                true
            }
            R.id.logout_option -> {
                firebaseAuth.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}
