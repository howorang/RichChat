package pl.dmcs.pb.richchat.app.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import butterknife.BindView
import butterknife.ButterKnife
import pl.dmcs.pb.richchat.R
import pl.dmcs.pb.richchat.app.BaseActivity

class MainActivity : BaseActivity() {

    @BindView(R.id.main_bottom_navigation_view)
    lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_chats -> true
                R.id.nav_public -> true
                R.id.nav_snippets -> true
                else -> false
            }
        }    }
}
