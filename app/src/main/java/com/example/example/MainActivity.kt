package com.example.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.example.demo1.Demo1Activity
import com.example.example.demo2.Demo2Activity
import com.example.example.demo3.Demo3Activity
import com.example.example.demo4.Demo4Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDemo1.setOnClickListener {
            startActivity(Intent(this, Demo1Activity::class.java))
        }
        btnDemo2.setOnClickListener {
            startActivity(Intent(this, Demo2Activity::class.java))
        }
        btnDemo3.setOnClickListener {
            startActivity(Intent(this, Demo3Activity::class.java))
        }
        btnDemo4.setOnClickListener {
            startActivity(Intent(this, Demo4Activity::class.java))
        }
    }


}
