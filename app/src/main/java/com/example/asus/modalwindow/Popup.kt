package com.example.asus.modalwindow

class Popup private constructor(builder: PopupBuilder) {

    val title: Int
    val subtitle: Int
    val image: Int
    val input: Int
    val positiveButton: Int
    val negativeButton: Int

    init {
        this.title = builder.title
        this.subtitle = builder.subtitle
        this.input = builder.input
        this.image = builder.image
        this.positiveButton = builder.positiveButton
        this.negativeButton = builder.negativeButton
    }

    companion object
    class PopupBuilder() {
        var title: Int = 0
        var subtitle: Int = 0
        var image: Int = 0
        var input: Int = 0
        var positiveButton: Int = 0
        var negativeButton: Int = 0

        fun addTitle(title: Int): PopupBuilder {
            this.title = title
            return this
        }

        fun addSubtitle(subtitle: Int): PopupBuilder {
            this.subtitle = subtitle
            return this
        }


        fun addImage(image: Int): PopupBuilder {
            this.image = image
            return this
        }

        fun addInput(input: Int): PopupBuilder {
            this.input = input
            return this
        }

        fun addPositiveButton(text: Int): PopupBuilder {
            this.positiveButton = text
            return this
        }


        fun addNegativeButton(text: Int): PopupBuilder {
            this.negativeButton = text
            return this
        }

        fun build(): FullDialogFragment {
            val popup = Popup(this)
            return FullDialogFragment.newInstance(popup)
        }

    }

}
