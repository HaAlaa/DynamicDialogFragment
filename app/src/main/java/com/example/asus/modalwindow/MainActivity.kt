package com.example.asus.modalwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.asus.modalwindow.Popup.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionDialogListener = object : FullDialogFragment.OnClickListener {
            override fun positiveClickListener() {

            }

            override fun negativeClickListener() {
            }
        }
        PopupBuilder()
                .addTitle(R.string.title)
                .addSubtitle(R.string.subtitle)
                .addInput(R.string.et_hint)
                .addPositiveButton(R.string.button1)
                .addNegativeButton(R.string.button2)
                .addImage(R.drawable.wallet)
                .build()
                .setmListener(actionDialogListener)
                .show(fragmentManager, "dialog")

    }
}
