package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    var back: Button? = null
    var result: TextView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        back = findViewById<View>(R.id.back_button) as Button
        result = findViewById<View>(R.id.result_box) as TextView
        val res = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        result!!.text = res
    }

    fun back(v: View) {
        when (v.id) {
            R.id.back_button -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else -> {}
        }
    }
}