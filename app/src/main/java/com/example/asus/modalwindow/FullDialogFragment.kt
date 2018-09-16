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
                putInt(Constants.TITLE, popup.title)
                putInt(Constants.SUBTITLE, popup.subtitle)
                putInt(Constants.IMAGE, popup.image)
                putInt(Constants.INPUT, popup.input)
                putInt(Constants.NEGATIVE_BUTTON, popup.negativeButton)
                putInt(Constants.POSITIVE_BUTTON, popup.positiveButton)
            }
        }
    }


    fun setmListener(mListener: OnClickListener): FullDialogFragment {
        this.mListener = mListener
        return this
    }


    private fun addTitle(text: Int) {
        title.setText(text)
        title.visibility = View.VISIBLE
    }


    private fun addSubtitle(text: Int) {
        subtitle.setText(text)
        subtitle.visibility = View.VISIBLE
    }

    private fun addImage(src: Int) {
        iv.setImageResource(src)
        iv.visibility = View.VISIBLE
    }


    private fun addInput(text: Int) {
        input.setHint(text)
        input.visibility = View.VISIBLE
    }

    private fun addNegativeButton(text: Int) {
        negative_button.setText(text)
        negative_button.visibility = View.VISIBLE
    }

    private fun addPositiveButton(text: Int) {
        positive_button.setText(text)
        positive_button.visibility = View.VISIBLE
        arrow.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let { it ->

            if (it.getInt(Constants.TITLE) != 0)
                addTitle(it.getInt(Constants.TITLE))

            if (it.getInt(Constants.SUBTITLE) != 0)
                addSubtitle(it.getInt(Constants.SUBTITLE))

            if (it.getInt(Constants.INPUT) != 0)
                addInput(it.getInt(Constants.INPUT))

            if (it.getInt(Constants.IMAGE) != 0)
                addImage(it.getInt(Constants.IMAGE))

            if (it.getInt(Constants.POSITIVE_BUTTON) != 0) {
                addPositiveButton(it.getInt(Constants.POSITIVE_BUTTON))
                positive_button.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.positiveClickListener()
                        dismiss()
                    }
                }
            }

            if (it.getInt(Constants.NEGATIVE_BUTTON) == 0) {
                centralizeButton()
            } else {
                addNegativeButton(it.getInt(Constants.NEGATIVE_BUTTON))
                negative_button.setOnClickListener() {
                    if (mListener != null) {
                        mListener?.negativeClickListener()
                        dismiss()
                    }
                }
            }
        }
    }

    private fun centralizeButton() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(parent)
        constraintSet.connect(positive_button.id, ConstraintSet.START, parent.id, ConstraintSet.START, 0)
        constraintSet.connect(positive_button.id, ConstraintSet.END, parent.id, ConstraintSet.END, 0)
        constraintSet.connect(positive_button.id, ConstraintSet.TOP, input.id, ConstraintSet.BOTTOM, 0)
        constraintSet.applyTo(parent)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.full_dialog_fragment, container, false)
    }

    interface OnClickListener {
        fun positiveClickListener()

        fun negativeClickListener()

    }
}