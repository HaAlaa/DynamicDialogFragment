package com.example.asus.modalwindow

import android.app.FragmentManager
import android.graphics.drawable.Drawable


class PopupCreator {

    companion object {
        fun showDialog(fragmentManager: FragmentManager, tvText: Int, buttonText: Int) {
            val dialogFragment = ButtonDialogFragment.newInstance(tvText, buttonText)
            dialogFragment.show(fragmentManager, "dialog")
        }

        fun showDialog(fragmentManager: FragmentManager, ivDrawable: Int, tv1Text: Int, tv2Text: Int, positiveButtonText: Int, negativeButtonText: Int) {
            val dialogFragment = DialogTwoActionsFragment.newInstance(ivDrawable, tv1Text, tv2Text, positiveButtonText, negativeButtonText)
            dialogFragment.show(fragmentManager, "dialog")
        }

        fun showDialog(fragmentManager: FragmentManager, tv1Text: Int, tv2Text: Int, positiveButtonText: Int, negativeButtonText: Int) {
            val dialogFragment = DialogTwoActionsFragment.newInstance(tv1Text, tv2Text, positiveButtonText, negativeButtonText)
            dialogFragment.show(fragmentManager, "dialog")
        }

        fun showDialog(fragmentManager: FragmentManager, tv1Text: Int, tv2Text: Int, etHintText: Int, positiveButtonText: Int, negativeButtonText: Int, hasInput: Boolean) {
            val dialogFragment = DialogInputFragment.newInstance(tv1Text, tv2Text, etHintText, positiveButtonText, negativeButtonText)
            dialogFragment.show(fragmentManager, "dialog")
        }
    }


}
