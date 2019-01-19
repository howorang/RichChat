package pl.dmcs.pb.richchat.app.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity
import pl.dmcs.pb.richchat.app.chat.list.ChatListFragment

class MainActivity : BaseActivity() {

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
                R.id.nav_snippets -> true
                else -> false
            }
        }
    }
}
