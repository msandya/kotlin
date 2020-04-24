package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)



        val database = Room.databaseBuilder(this,FriendDatabase:: class.java,"friend_Database")
            .allowMainThreadQueries()
            .build()

        database.friendDao().insertFriend(Friend(firstName = "Nathan", rating = 1000))

        val allFriends = database.friendDao().getAllFriends()

        //friendsRecyclerView.apply{
        //   layoutManager = LinearLayoutManager(this@MainActivity)
        //adapter = FriendAdapter(allFriends)

        //}



        allFriends.forEach {
            friendsTextView.append("\n" + it.firstName)
        }
        d("daniel","allFriends size? ${allFriends.size}")
    }
}
