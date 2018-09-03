package com.example.asus.modalwindow

import android.annotation.SuppressLint
import android.app.DialogFragment
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.zip.Inflater

public class DialogTwoActionsFragment : DialogFragment() {

    lateinit var listener: ActionListner
    var withArt = false
    lateinit var inf: View

    companion object {
        @JvmStatic

        fun newInstance(withArt: Boolean) = DialogTwoActionsFragment().apply {
            arguments = Bundle().apply {
                putBoolean("withArt", withArt)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            withArt = it.getBoolean("withArt")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (activity is ActionListner) {
            listener = activity as ActionListner
        }

        if (!withArt) {
            inf = inflater?.inflate(R.layout.dialog_two_action_fragment, container, false)!!

            val cancel = inf!!.findViewById(R.id.cancel) as Button
            cancel.setOnClickListener {
                listener.doNegativeClick(ModalWindowType.TWO_ACTION);
                dismiss()

            }
            val submit = inf!!.findViewById(R.id.submit) as Button
            submit.setOnClickListener {
                listener.doPositiveClick(ModalWindowType.TWO_ACTION);
                dismiss()
            }

        } else {
            inf = inflater?.inflate(R.layout.dialog_art_two_action_fragment, container, false)!!

            val cancel = inf!!.findViewById(R.id.cancel) as Button
            cancel.setOnClickListener {
                listener.doNegativeClick(ModalWindowType.ART_TWO_ACTION);
                dismiss()

            }
            val submit = inf!!.findViewById(R.id.submit) as Button
            submit.setOnClickListener {
                listener.doPositiveClick(ModalWindowType.ART_TWO_ACTION);
                dismiss()
            }
        }
        return inf
    }
}

/*
    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)

        Log.i("eeeeeee", "in attach")
        if (activity is ActionListner) {
            listener = activity as ActionListner
        } else {
            Log.i("eeeeeee", "error")
            // Throw an error!
        }
    }
        */


