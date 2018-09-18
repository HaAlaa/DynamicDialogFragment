package com.example.asus.modalwindow

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.asus.modalwindow.popup.PopupDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val actionDialogListener = object : FullDialogFragment.OnClickListener {
//            override fun click() {
//            }
//        }
//        val popup=Popup.PopupBuilder(applicationContext)
//                .addTitle("Hello")
//                .addSubtitle("you need to login")
//                .addFirstButton("ok")
//                .build()
//                .setmListener(actionDialogListener)
//                .show(fragmentManager, "dialog")
        val dialog = PopupDialog.Builder(this).setCancelable(true)
                .setTitle("Title")
                .setIcon(R.drawable.wallet)
                .setMessage("Message")
                .setPositiveButton("sadsa", DialogInterface.OnClickListener { p0, p1 -> Toast.makeText(applicationContext, "positive", Toast.LENGTH_LONG).show() })
                .setNegativeButton("negative", DialogInterface.OnClickListener { p0, p1 -> Toast.makeText(applicationContext, "negative", Toast.LENGTH_LONG).show() })
                .show()
    }
}
