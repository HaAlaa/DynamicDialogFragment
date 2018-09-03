package com.example.asus.modalwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), ActionListner {
    override fun doNegativeClick(windowType: ModalWindowType) {
        Log.i("positive",windowType .toString() )
    }

    override fun doPositiveClick(windowType: ModalWindowType) {
        Log.i("negative",windowType .toString() )

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val windowType = ModalWindowType.ART_TWO_ACTION;
        val ft = fragmentManager.beginTransaction()

        when (windowType) {
            ModalWindowType.BUTTON -> {
                val dialogFragment = ButtonDialogFragment.newInstance()
                dialogFragment.show(ft, "dialog")
            }

            ModalWindowType.INPUT -> {
                val dialogFragment = DialogInputFragment.newInstance()
                dialogFragment.show(ft, "dialog")
            }

            ModalWindowType.ART_TWO_ACTION -> {
                val dialogFragment = DialogTwoActionsFragment.newInstance(true)
                dialogFragment.show(ft, "dialog")
            }

            ModalWindowType.TWO_ACTION -> {
                val dialogFragment = DialogTwoActionsFragment.newInstance(false)
                dialogFragment.show(ft, "dialog")
            }

        }
    }
}
