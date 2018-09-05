package com.example.asus.modalwindow

import android.support.constraint.ConstraintLayout
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class DialogObject {
    private var iv: ImageView
    private var title: TextView
    private var subtitle: TextView
    private var input: EditText
    private var firstButton: Button
    private var secondButton: Button
    private var parentLayout: ConstraintLayout

    constructor(iv: ImageView, title: TextView, subtitle: TextView, input: EditText, firstButton: Button, secondButton: Button, parentLayout: ConstraintLayout) {
        this.iv = iv
        this.title = title
        this.subtitle = subtitle
        this.input = input
        this.firstButton = firstButton
        this.secondButton = secondButton
        this.parentLayout = parentLayout
    }

    public fun getImg(): ImageView {
        return iv
    }

    public fun getTitle(): TextView {
        return title
    }

    public fun getSubtitle(): TextView {
        return subtitle
    }

    public fun getInput(): EditText {
        return input
    }

    public fun getFirstButton(): Button {
        return firstButton
    }

    public fun getSecondButton(): Button {
        return secondButton
    }

    public fun getParentLayout(): ConstraintLayout {
        return parentLayout
    }

    public fun setImg(iv: ImageView) {
        this.iv = iv
    }

    public fun setTitle(title: TextView) {
        this.title = title
    }

    public fun setSubtitle(subTitle: TextView) {
        this.subtitle = subtitle
    }

    public fun setInput(input: EditText) {
        this.input = input
    }

    public fun setFirstButton(first: Button) {
        this.firstButton = first
    }

    public fun setSecondButton(second: Button) {
        this.secondButton = second
    }

    public fun setParentLayout(layout: ConstraintLayout) {
        this.parentLayout = layout
    }


}