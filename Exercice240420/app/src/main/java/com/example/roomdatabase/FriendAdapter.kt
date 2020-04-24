package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter(allFriends: List<Friend>) :
    RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    private var dataset: MutableList<Friend> = allFriends.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.friend_row, parent, false))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.view.name.text = allFriends[position].firstName
        val friend = dataset[position]
        holder.name.setText( friend.firstName )
        holder.rating.setText( friend.rating.toString() )

        holder.edit.setOnClickListener {
            val editFriend = Friend(
                uid = dataset[position].uid,
                firstName = holder.name.text.toString(),
                rating = holder.rating.text.toString().toInt()
            )
            eventListener?.onFriendEdit(editFriend)
        }
            holder.remove.setOnClickListener { eventListener?.onFriendDelete(dataset[position]) }
    }

    fun updateData( friendList: List<Friend> ) {
        dataset.clear()
        dataset.addAll(friendList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size

    private var eventListener: EventListener? = null
    fun setEventListener(eventListener: EventListener) {
        this.eventListener = eventListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<EditText>(R.id.name)!!
        val rating = view.findViewById<EditText>(R.id.rating)!!
        val edit = view.findViewById<Button>(R.id.edit)!!
        val remove = view.findViewById<Button>(R.id.remove)!!
    }

    interface EventListener {
        fun onFriendEdit(friend: Friend)
        fun onFriendDelete(friend: Friend)
    }
}