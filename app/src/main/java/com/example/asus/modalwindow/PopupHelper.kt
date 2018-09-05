package com.example.asus.modalwindow

import android.app.FragmentManager

class PopupHelper {

    private var title: Int = 0
    private var subtitle: Int = 0
    private var IV: Int = 0
    private var input: Int = 0
    private var firstButton: Int = 0
    private var secondButton: Int = 0


    fun addTitle(text: Int) {
        title = text
    }

    fun addSubtitle(text: Int) {
        subtitle = text
    }

    fun addImage(src: Int) {
        IV = src
    }

    fun addInput(text: Int) {
        input = text
    }


    fun addFirstButton(text: Int) {
        firstButton = text
    }

    fun addSecondButton(text: Int) {
        secondButton = text
    }


    fun showDialog(fragmentManager: FragmentManager) {
        val dialogFragment = FullDialogFragment.newInstance(title, subtitle, IV, input, firstButton, secondButton)
        dialogFragment.show(fragmentManager, "dialog")
    }

}



