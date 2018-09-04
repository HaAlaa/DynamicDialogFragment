package com.example.asus.modalwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // PopupCreator.showDialog(fragmentManager, R.drawable.wallet,  R.string.tv1, R.string.tv2, R.string.button,R.string.button2)
        PopupCreator.showDialog(fragmentManager, R.string.tv1,  R.string.tv3, R.string.et_hint, R.string.button,R.string.button3, true)


    }
}
