package com.example.asus.modalwindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.app.DialogFragment
import android.support.constraint.ConstraintLayout
import android.widget.EditText
import android.widget.ImageView
import android.support.constraint.ConstraintSet

public class FullDialogFragment : DialogFragment() {

    lateinit var obj: DialogObject
    lateinit var listener: DialogListener


    companion object {
        fun newInstance(title: Int, subtitle: Int, IV: Int, input: Int, firstButton: Int, secondButton: Int) = FullDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("title", title)
                putInt("subtitle", subtitle)
                putInt("IV", IV)
                putInt("input", input)
                putInt("firstButton", firstButton)
                putInt("secondButton", secondButton)
            }
        }
    }


    fun addTitle(text: Int) {
        obj.getTitle().setText(text)
        obj.getTitle().setVisibility(View.VISIBLE)
    }


    fun addSubtitle(text: Int) {
        obj.getSubtitle().setText(text)
        obj.getSubtitle().setVisibility(View.VISIBLE)
    }

    fun addImage(src: Int) {
        obj.getImg().setImageResource(src)
        obj.getImg().setVisibility(View.VISIBLE)
    }

    fun addInput(text: Int) {
        obj.getInput().setHint(text)
        obj.getInput().setVisibility(View.VISIBLE)
    }

    fun addFirstButton(text: Int) {
        obj.getFirstButton().setText(text)
        obj.getFirstButton().setVisibility(View.VISIBLE)
    }

    fun addSecondButton(text: Int) {
        obj.getSecondButton().setText(text)
        obj.getSecondButton().setVisibility(View.VISIBLE)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.full_dialog_fragment, container, false)

        val parent = inflater?.findViewById(R.id.parent) as ConstraintLayout

        val iv = inflater?.findViewById(R.id.iv) as ImageView
        val title = inflater?.findViewById(R.id.title) as TextView
        val subtitle = inflater?.findViewById(R.id.subtitle) as TextView
        val input = inflater?.findViewById(R.id.input) as EditText
        val firstButton = inflater?.findViewById(R.id.first_button) as Button
        val secondButton = inflater?.findViewById(R.id.second_button) as Button
        obj = DialogObject(iv, title, subtitle, input, firstButton, secondButton, parent)



        arguments?.let {
            if (it.getInt("title") != 0)
                addTitle(it.getInt("title"))
            else
                obj.getTitle().setVisibility(View.GONE)


            if (it.getInt("subtitle") != 0)
                addSubtitle(it.getInt("subtitle"))
            else
                obj.getSubtitle().setVisibility(View.GONE)


            if (it.getInt("IV") != 0)
                addImage(it.getInt("IV"))
            else
                obj.getImg().setVisibility(View.GONE)


            if (it.getInt("input") != 0)
                addInput(it.getInt("input"))
            else
                obj.getInput().setVisibility(View.GONE)



            if (it.getInt("firstButton") != 0) {
                addFirstButton(it.getInt("firstButton"))
                obj.getFirstButton().setOnClickListener() {
                    if (activity is DialogListener) {
                        listener = activity as DialogListener
                        listener.myListener(obj);
                    }
                    dismiss()
                }
            } else
                obj.getFirstButton().setVisibility(View.GONE)



            if (it.getInt("secondButton") != 0) {
                addSecondButton(it.getInt("secondButton"))
                obj.getSecondButton().setOnClickListener() {
                    if (activity is DialogListener) {
                        listener = activity as DialogListener
                        listener.myListener(obj);
                    }
                    dismiss()
                }
            } else {
                obj.getSecondButton().setVisibility(View.GONE)

                //set the button to the center of the layout
                val constraintSet = ConstraintSet()
                constraintSet.clone(obj.getParentLayout())
                constraintSet.connect(obj.getFirstButton().getId(), ConstraintSet.START, obj.getParentLayout().getId(), ConstraintSet.START, 0)
                constraintSet.connect(obj.getFirstButton().getId(), ConstraintSet.END, obj.getParentLayout().getId(), ConstraintSet.END, 0)
                constraintSet.applyTo(obj.getParentLayout())

            }


        }

        return inflater
    }

}