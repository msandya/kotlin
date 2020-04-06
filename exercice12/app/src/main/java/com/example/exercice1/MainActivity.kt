package com.example.exercice1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun save(view: View) {
        val intent = Intent(this@MainActivity,ResultActivity::class.java)
        intent.putExtra(Constant.SAVEDText, editText.text.toString())
        startActivity(intent)
    }
}
