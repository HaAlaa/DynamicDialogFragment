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

class FullDialogFragment : DialogFragment() {

    private var mListener: OnClickListener? = null

    lateinit var parent: ConstraintLayout
    lateinit var iv: ImageView
    lateinit var title: TextView
    lateinit var subtitle: TextView
    lateinit var input: EditText
    lateinit var firstButton: Button
    lateinit var secondButton: Button


    companion object {
        fun newInstance(title: Int, subtitle: Int, IV: Int, input: Int, firstButton: Int, secondButton: Int) = FullDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TITLE, title)
                putInt(Constants.SUBTITLE, subtitle)
                putInt(Constants.IMAGE, IV)
                putInt(Constants.INPUT, input)
                putInt(Constants.FIRST_BUTTON, firstButton)
                putInt(Constants.SECOND_BUTTON, secondButton)
            }
        }

        fun newInstance(title: String, subtitle: String, IV: Int, input: String, firstButton: String, secondButton: String) = FullDialogFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.TITLE, title)
                putString(Constants.SUBTITLE, subtitle)
                putInt(Constants.IMAGE, IV)
                putString(Constants.INPUT, input)
                putString(Constants.FIRST_BUTTON, firstButton)
                putString(Constants.SECOND_BUTTON, secondButton)
            }
        }
    }


    fun setmListener(mListener: OnClickListener): FullDialogFragment {
        this.mListener = mListener
        return this
    }

    fun addTitle(text: Int) {
        title.setText(text)
        title.setVisibility(View.VISIBLE)
    }

    fun addTitle(text: String) {
        title.setText(text)
        title.setVisibility(View.VISIBLE)
    }


    fun addSubtitle(text: Int) {
        subtitle.setText(text)
        subtitle.setVisibility(View.VISIBLE)
    }

    fun addSubtitle(text: String) {
        subtitle.setText(text)
        subtitle.setVisibility(View.VISIBLE)
    }


    fun addImage(src: Int) {
        iv.setImageResource(src)
        iv.setVisibility(View.VISIBLE)
    }

    fun addInput(text: Int) {
        input.setHint(text)
        input.setVisibility(View.VISIBLE)
    }

    fun addInput(text: String) {
        input.setHint(text)
        input.setVisibility(View.VISIBLE)
    }

    fun addFirstButton(text: Int) {
        firstButton.setText(text)
        firstButton.setVisibility(View.VISIBLE)
    }

    fun addFirstButton(text: String) {
        firstButton.setText(text)
        firstButton.setVisibility(View.VISIBLE)
    }

    fun addSecondButton(text: Int) {
        secondButton.setText(text)
        secondButton.setVisibility(View.VISIBLE)
    }

    fun addSecondButton(text: String) {
        secondButton.setText(text)
        secondButton.setVisibility(View.VISIBLE)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.full_dialog_fragment, container, false)

        parent = inflater?.findViewById(R.id.parent) as ConstraintLayout

        iv = inflater?.findViewById(R.id.iv) as ImageView
        title = inflater?.findViewById(R.id.title) as TextView
        subtitle = inflater?.findViewById(R.id.subtitle) as TextView
        input = inflater?.findViewById(R.id.input) as EditText
        firstButton = inflater?.findViewById(R.id.first_button) as Button
        secondButton = inflater?.findViewById(R.id.second_button) as Button


        arguments?.let {

            if (it.getInt(Constants.TITLE) != 0)
                addTitle(it.getInt(Constants.TITLE))
            else if (it.getString(Constants.TITLE) != "" && it.getString(Constants.TITLE) != null)
                addTitle(it.getString(Constants.TITLE))
            else title.setVisibility(View.GONE)


            if (it.getInt(Constants.SUBTITLE) != 0)
                addSubtitle(it.getInt(Constants.SUBTITLE))
            else if (it.getString(Constants.SUBTITLE) != "" && it.getString(Constants.SUBTITLE) != null)
                addSubtitle(it.getString(Constants.SUBTITLE))
            else subtitle.setVisibility(View.GONE)


            if (it.getInt(Constants.IMAGE) == 0)
                iv.setVisibility(View.GONE)
            else
                addImage(it.getInt(Constants.IMAGE))


            if (it.getInt(Constants.INPUT) != 0)
                addInput(it.getInt(Constants.INPUT))
            else if (it.getString(Constants.INPUT) != "" && it.getString(Constants.INPUT) != null) {
                addInput(it.getString(Constants.INPUT))
            } else
                input.setVisibility(View.GONE)



            if (it.getInt(Constants.FIRST_BUTTON) != 0) {
                addFirstButton(it.getInt(Constants.FIRST_BUTTON))
                firstButton.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.click()
                    }
                    dismiss()
                }
            } else if (it.getString(Constants.FIRST_BUTTON) != "" && it.getString(Constants.FIRST_BUTTON) != null) {
                addFirstButton(it.getString(Constants.FIRST_BUTTON))
                firstButton.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.click()
                        dismiss()
                    }
                }
            } else
                firstButton.setVisibility(View.GONE)


            if (it.getInt(Constants.SECOND_BUTTON) != 0) {
                addSecondButton(it.getInt(Constants.SECOND_BUTTON))
                secondButton.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.click()
                        dismiss()
                    }
                }
            } else if (it.getString(Constants.SECOND_BUTTON) != "" && it.getString(Constants.SECOND_BUTTON) != null) {
                addSecondButton(it.getString(Constants.SECOND_BUTTON))
                secondButton.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.click()
                        dismiss()
                    }
                }
            } else {
                secondButton.setVisibility(View.GONE)

                //set the button to the center of the layout
                val constraintSet = ConstraintSet()
                constraintSet.clone(parent)
                constraintSet.connect(firstButton.getId(), ConstraintSet.START, parent.getId(), ConstraintSet.START, 0)
                constraintSet.connect(firstButton.getId(), ConstraintSet.END, parent.getId(), ConstraintSet.END, 0)
                constraintSet.applyTo(parent)
            }
        }
        return inflater
    }

    interface OnClickListener {
        fun click()
    }
}