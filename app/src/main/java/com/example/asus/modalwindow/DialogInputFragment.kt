package com.example.asus.modalwindow

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DialogInputFragment : DialogFragment() {

    companion object {
        fun newInstance(): DialogInputFragment {
            val fragment = DialogInputFragment()
            return fragment
        }

        /*fun newInstance(ID: Int) = MyDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("type", ID)
            }
        }
        */

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.button_dialog_fragment, container, false)
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
