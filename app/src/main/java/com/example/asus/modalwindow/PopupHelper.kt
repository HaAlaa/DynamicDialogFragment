package com.example.asus.modalwindow

import android.app.FragmentManager

class PopupHelper {
    private var title: Int = 0
    private var subtitle: Int = 0
    private var IV: Int = 0
    private var input: Int = 0
    private var firstButton: Int = 0
    private var secondButton: Int = 0

    private var titleString: String = ""
    private var subtitleString: String = ""
    private var inputString: String = ""
    private var firstButtonString: String = ""
    private var secondButtonString: String = ""


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

    fun addTitle(text: String) {
        titleString = text
    }

    fun addSubtitle(text: String) {
        subtitleString = text
    }


    fun addInput(text: String) {
        inputString = text
    }


    fun addFirstButton(text: String) {
        firstButtonString = text
    }

    fun addSecondButton(text: String) {
        secondButtonString = text
    }


    fun builder(): FullDialogFragment {
        if (title != 0 || subtitle != 0 || IV != 0 || input != 0 || firstButton != 0 ||secondButton != 0)
            return FullDialogFragment.newInstance(title, subtitle, IV, input, firstButton, secondButton)
        else
            return FullDialogFragment.newInstance(titleString, subtitleString, IV, inputString, firstButtonString, secondButtonString)
    }

}



