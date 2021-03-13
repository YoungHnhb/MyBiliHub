package com.liyy.bilihub

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.liyy.bilihub.chapter1.MainActivityC1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun toChapter1(view: View) {
        startActivity(Intent(this, MainActivityC1::class.java))
    }
}