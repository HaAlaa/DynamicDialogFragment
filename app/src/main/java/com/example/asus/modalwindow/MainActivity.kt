package com.example.asus.modalwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionDialogListener = object : FullDialogFragment.OnClickListener {
            override fun click() {
            }
        }
        val popup=Popup.PopupBuilder(applicationContext)
                .addTitle("Hello")
                .addSubtitle("you need to login")
                .addFirstButton("ok")
                .build()
                .setmListener(actionDialogListener)
                .show(fragmentManager, "dialog")

    }
}
