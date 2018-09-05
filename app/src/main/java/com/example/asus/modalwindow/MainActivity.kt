package com.example.asus.modalwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), DialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper = PopupHelper()
        helper.addTitle(R.string.tv1)
        helper.addSubtitle(R.string.tv2)
        helper.addFirstButton(R.string.button1)
        helper.addSecondButton(R.string.button2)
        helper.showDialog(fragmentManager)
    }

    override fun myListener(dialogObject: DialogObject) {
        dialogObject.getFirstButton().setOnClickListener() {
        }

        dialogObject.getSecondButton().setOnClickListener() {
        }
    }
}
