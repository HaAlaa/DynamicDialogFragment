package com.example.asus.modalwindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.DialogFragment
import android.support.constraint.ConstraintSet

import kotlinx.android.synthetic.main.full_dialog_fragment.*

class FullDialogFragment : DialogFragment() {

    private var mListener: OnClickListener? = null

    companion object {
        fun newInstance(popup: Popup) = FullDialogFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.TITLE, popup.title)
                putString(Constants.SUBTITLE, popup.subtitle)
                putInt(Constants.IMAGE, popup.image)
                putString(Constants.INPUT, popup.input)
                putString(Constants.FIRST_BUTTON, popup.firstButton)
                putString(Constants.SECOND_BUTTON, popup.secondButton)
            }
        }
    }


    fun setmListener(mListener: OnClickListener): FullDialogFragment {
        this.mListener = mListener
        return this
    }


    fun addTitle(text: String) {
        title.text = text
        title.setVisibility(View.VISIBLE)
    }


    fun addSubtitle(text: String) {
        subtitle.setText(text)
        subtitle.setVisibility(View.VISIBLE)
    }


    fun addImage(src: Int) {
        iv.setImageResource(src)
        iv.setVisibility(View.VISIBLE)
    }


    fun addInput(text: String) {
        input.hint = text
        input.setVisibility(View.VISIBLE)
    }


    fun addFirstButton(text: String) {
        first_button.setText(text)
        first_button.setVisibility(View.VISIBLE)
    }


    fun addSecondButton(text: String) {
        second_button.setText(text)
        second_button.setVisibility(View.VISIBLE)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {

            if (it.getString(Constants.TITLE) == "")
                title.setVisibility(View.GONE)
            else {
                addTitle(it.getString(Constants.TITLE))
            }

            if (it.getString(Constants.SUBTITLE) == "")
                subtitle.setVisibility(View.GONE)
            else {
                addSubtitle(it.getString(Constants.SUBTITLE))
            }

            if (it.getString(Constants.INPUT) == "") {

                input.setVisibility(View.GONE)
            } else {
                addInput(it.getString(Constants.INPUT))
            }


            if (it.getInt(Constants.IMAGE) == 0)
                iv.setVisibility(View.GONE)
            else {
                addImage(it.getInt(Constants.IMAGE))
            }

            if (it.getString(Constants.FIRST_BUTTON) == "")
                first_button.setVisibility(View.GONE)
            else {
                addFirstButton(it.getString(Constants.FIRST_BUTTON))
                first_button.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.click()
                        dismiss()
                    }
                }
            }

            if (it.getString(Constants.SECOND_BUTTON) == "") {
                second_button.setVisibility(View.GONE)
                centralizeButton()
            } else {
                addSecondButton(it.getString(Constants.SECOND_BUTTON))
                second_button.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.click()
                        dismiss()
                    }
                }
            }
        }
    }

    fun centralizeButton() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(parent)
        constraintSet.connect(first_button.id, ConstraintSet.START, parent.id, ConstraintSet.START, 0)
        constraintSet.connect(first_button.id, ConstraintSet.END, parent.id, ConstraintSet.END, 0)
        constraintSet.applyTo(parent)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.full_dialog_fragment, container, false)

        return inflater
    }

    interface OnClickListener {
        fun click()
    }
}