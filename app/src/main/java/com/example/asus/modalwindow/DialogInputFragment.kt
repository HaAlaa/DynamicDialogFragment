package com.example.asus.modalwindow

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class DialogInputFragment : DialogFragment() {

    companion object {


        fun newInstance(tv1Text: Int, tv2Text: Int, etHintText: Int, positiveButtonText: Int, negativeButtonText: Int) = DialogInputFragment().apply {
            arguments = Bundle().apply {
                putInt("tv1Text", tv1Text)
                putInt("tv2Text", tv2Text)
                putInt("etHintText", etHintText)

                putInt("positiveButtonText", positiveButtonText)
                putInt("negativeButtonText", negativeButtonText)
            }
        }
    }


    fun positiveAction() {}
    fun negativeAction() {}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.dialog_input_fragment, container, false)

        val tv1 = inflater?.findViewById(R.id.tv1) as TextView
        val tv2 = inflater?.findViewById(R.id.tv2) as TextView
        val et = inflater?.findViewById(R.id.et) as EditText

        val cancel = inflater?.findViewById(R.id.cancel) as Button
        val submit = inflater?.findViewById(R.id.submit) as Button


        arguments?.let {
            tv1.setText(it.getInt("tv1Text"))
            tv2.setText(it.getInt("tv2Text"))
            et.setHint(it.getInt("etHintText"))
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