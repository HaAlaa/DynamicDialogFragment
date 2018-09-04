package com.example.asus.modalwindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.app.DialogFragment
import android.graphics.drawable.Drawable
import android.widget.ImageView


public class DialogTwoActionsFragment : DialogFragment() {
    var withArt = false

    companion object {
        fun newInstance(ivDrawable: Int, tv1Text: Int, tv2Text: Int, positiveButtonText: Int, negativeButtonText: Int) = DialogTwoActionsFragment().apply {
            arguments = Bundle().apply {
                putInt("ivDrawable", ivDrawable)
                putInt("tv1Text", tv1Text)
                putInt("tv2Text", tv2Text)
                putInt("positiveButtonText", positiveButtonText)
                putInt("negativeButtonText", negativeButtonText)

                withArt = true
            }
        }

        fun newInstance(tv1Text: Int, tv2Text: Int, positiveButtonText: Int, negativeButtonText: Int) = DialogTwoActionsFragment().apply {
            arguments = Bundle().apply {
                putInt("tv1Text", tv1Text)
                putInt("tv2Text", tv2Text)
                putInt("positiveButtonText", positiveButtonText)
                putInt("negativeButtonText", negativeButtonText)

                withArt = false
            }
        }
    }


    fun positiveAction() {}
    fun negativeAction() {}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.dialog_art_two_action_fragment, container, false)

        val iv = inflater?.findViewById(R.id.iv) as ImageView
        val tv1 = inflater?.findViewById(R.id.tv1) as TextView
        val tv2 = inflater?.findViewById(R.id.tv2) as TextView
        val cancel = inflater?.findViewById(R.id.cancel) as Button
        val submit = inflater?.findViewById(R.id.submit) as Button

        if (!withArt)
            iv.setVisibility(View.GONE)
        else
            arguments?.let {
                iv.setImageResource(it.getInt("ivDrawable"))
            }

        arguments?.let {
            tv1.setText(it.getInt("tv1Text"))
            tv2.setText(it.getInt("tv2Text"))
            cancel.setText(it.getInt("positiveButtonText"))
            submit.setText(it.getInt("negativeButtonText"))

        }

        cancel.setOnClickListener {
            negativeAction()
            dismiss()
        }
        submit.setOnClickListener {
            positiveAction()
            dismiss()
        }
        return inflater
    }

}