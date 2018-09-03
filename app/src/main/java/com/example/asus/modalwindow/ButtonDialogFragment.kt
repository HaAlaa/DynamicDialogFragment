package com.example.asus.modalwindow

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ButtonDialogFragment : DialogFragment() {

    lateinit var listener: ActionListner

    companion object {
        fun newInstance(): ButtonDialogFragment {
            return newInstance()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflater= inflater?.inflate(R.layout.button_dialog_fragment, container, false)
        val submit = inflater!!.findViewById(R.id.submit) as Button

        submit.setOnClickListener {
            if (activity is ActionListner) {
                listener = activity as ActionListner
                listener.doPositiveClick(ModalWindowType.ART_TWO_ACTION);
            }
            dismiss()
        }
        return  inflater

    }


/*
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("title")
                //   .setIcon(R.drawable.alert_dialog_icon)
                .setView(R.layout.button_dialog_fragment)
                .setNegativeButton("Cancel", { dialog, whichButton ->
                    dialog.cancel()

                })
                .setPositiveButton("next", { d, i ->
                    //
                    /*
                     new DialogInterface . OnClickListener () {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((FragmentAlertDialog) getActivity ()).doPositiveClick();
                            }
                        }
                     */
                })
        return builder.create()
*/

}
