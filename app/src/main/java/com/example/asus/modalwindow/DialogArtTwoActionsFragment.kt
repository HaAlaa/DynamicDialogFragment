package com.example.asus.modalwindow

import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

public class DialogArtTwoActionsFragment : DialogFragment() {


     lateinit var listener: ActionListner

    companion object {
        fun newInstance(): DialogArtTwoActionsFragment {
            return newInstance()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater = inflater?.inflate(R.layout.dialog_art_two_action_fragment, container, false)
        val cancel = inflater!!.findViewById(R.id.cancel) as Button
        cancel.setOnClickListener {
            listener.doNegativeClick(ModalWindowType.ART_TWO_ACTION);
            dismiss()

        }


        return inflater
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        if (activity is ActionListner) {
            listener = activity as ActionListner
        } else {
            // Throw an error!
        }
    }


}
