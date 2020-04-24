package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room
            .databaseBuilder(
                this,
                FriendDatabase::class.java, "friend_database"
            ).allowMainThreadQueries()
            .build()

        var allFriends = database.friendDao().getAllFriends()

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = FriendAdapter(allFriends)
        fun refreshAdapter() = viewAdapter.updateData(database.friendDao().getAllFriends())

        viewAdapter.setEventListener(
            object : FriendAdapter.EventListener {
                override fun onFriendEdit(friend: Friend) {
                    database.friendDao().updateFriend(friend)
                    refreshAdapter()
                }

                override fun onFriendDelete(friend: Friend) {
                    database.friendDao().deleteFriend(friend)
                    refreshAdapter()
                }
            }
        )

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        findViewById<Button>(R.id.bAdd).setOnClickListener {
            database.friendDao().insertFriend(Friend(firstName = "", rating = 0))
            refreshAdapter()
        }
    }
}
