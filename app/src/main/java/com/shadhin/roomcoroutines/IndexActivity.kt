package com.shadhin.roomcoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IndexActivity : AppCompatActivity() {
    private lateinit var btnRoom: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        init();
        buttonAction();
    } private fun init() {
        btnRoom = findViewById(R.id.btnRoom)
    }

    private fun buttonAction() {

        btnRoom.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            startActivity(intent);
        };
    }
}
