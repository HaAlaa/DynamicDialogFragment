package com.example.asus.modalwindow

import android.app.DialogFragment
import android.app.FragmentManager
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ButtonDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(tvText: Int, buttonText: Int) = ButtonDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("tvText", tvText)
                putInt("buttonText", buttonText)
            }
        }
    }


    fun positiveAction() {}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.button_dialog_fragment, container, false)
        val tv = inflater?.findViewById(R.id.tv) as TextView
        val submit = inflater?.findViewById(R.id.submit) as Button

        arguments?.let {
            tv.setText(it.getInt("tvText"))
            submit.setText(it.getInt("buttonText"))
        }

        submit.setOnClickListener {
            positiveAction()
            dismiss()
        }
        return inflater
    }

}
