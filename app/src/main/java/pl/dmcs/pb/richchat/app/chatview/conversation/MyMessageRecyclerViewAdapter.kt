package pl.dmcs.pb.richchat.app.chatview.conversation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.dmcs.pb.richchat.R

class MyMessageRecyclerViewAdapter(
) : RecyclerView.Adapter<MyMessageRecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return 0;
    }

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_message_received, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }



    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

    }
}
