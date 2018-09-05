package com.example.asus.modalwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val helper = PopupHelper()
        helper.addTitle(R.string.app_name)
        helper.addSubtitle(R.string.tv2)
        helper.addInput(R.string.et_hint)
        helper.addFirstButton(R.string.button)
        helper.showDialog(fragmentManager)
    }
}
