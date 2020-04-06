package com.example.exercice2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val (userMail, userPsw) = restore()
        editEmail.setText(userMail)
        editPassword.setText(userPsw)

        emailView.text = userMail
        pswView.text = userPsw
    }
    fun onSave(view: View) {
        val userMail = editEmail.text.toString()
        val userPass = editPassword.text.toString()

        if (userMail.isEmpty()) {
            editEmail.error = Constant.ER_EMAIL
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher((userMail)).matches()) {
            editEmail.error = Constant.ER_EMAIL
            return
        }
        if (userPass.isEmpty()) {
            editPassword.error = Constant.ER_PASSWORD
            return
        }
        if (save(Pair(userMail, userPass))) {
            Toast.makeText(applicationContext, Constant.INFORMATION_SAVED, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, Constant.ER_SAVED, Toast.LENGTH_SHORT).show()
        }

        pswView.text = userPass
        emailView.text = userMail
    }

    private fun save (userInfo: Pair<String, String>): Boolean {
        val (userMail, userPass) = userInfo
        val sharedPref = getSharedPreferences(Constant.NAME, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()

        edit.putString(Constant.EMAIL, userMail)
        edit.putString(Constant.PWD, userPass)
        return edit.commit()
    }

    private fun restore(): Pair<String, String> {
        val sharedPref = getSharedPreferences(Constant.NAME, Context.MODE_PRIVATE)
        return Pair(sharedPref.getString(Constant.EMAIL, "")!!, sharedPref.getString(Constant.PWD, "")!!)
    }
}
