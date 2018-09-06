package com.example.asus.modalwindow

import android.content.Context
import android.util.SparseArray

class Popup private constructor(builder: PopupBuilder) {

    val title: String
    val subtitle: String
    val image: Int
    val input: String
    val firstButton: String
    val secondButton: String

    init {
        this.title = builder.title
        this.subtitle = builder.subtitle
        this.input = builder.input
        this.image = builder.image
        this.firstButton = builder.firstButton
        this.secondButton = builder.secondButton
    }

    companion object
    class PopupBuilder() {
        lateinit var context: Context

        var title: String = ""
        var subtitle: String = ""
        var image: Int = 0
        var input: String = ""
        var firstButton: String = ""
        var secondButton: String = ""


        fun addTitle(title: String): PopupBuilder {
            this.title = title
            return this
        }

        fun addTitle(title: Int): PopupBuilder {
            this.title = context.resources.getString(title)
            return this
        }

        fun addSubtitle(subtitle: Int): PopupBuilder {
            this.subtitle = context.resources.getString(subtitle)
            return this
        }

        fun addSubtitle(subtitle: String): PopupBuilder {
            this.subtitle = subtitle
            return this
        }


        fun addImage(image: Int): PopupBuilder {
            this.image = image
            return this
        }


        fun addInput(input: Int): PopupBuilder {
            this.input = context.resources.getString(input)
            return this
        }

        fun addInput(input: String): PopupBuilder {
            this.input = input
            return this
        }

        fun addFirstButton(text: Int): PopupBuilder {
            this.firstButton = context.resources.getString(text)
            return this
        }

        fun addFirstButton(text: String): PopupBuilder {
            this.firstButton = text
            return this
        }

        fun addSecondButton(text: Int): PopupBuilder {
            this.secondButton = context.resources.getString(text)
            return this
        }

        fun addSecondButton(text: String): PopupBuilder {
            this.secondButton = text
            return this
        }

        fun build(): FullDialogFragment {
            val popup = Popup(this)
            return FullDialogFragment.newInstance(popup)
        }

    }

}
