package com.example.asus.modalwindow.popup

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Message
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.util.Log
import android.widget.Button

import com.example.asus.modalwindow.R

class PopupDialog internal constructor(context: Context, @StyleRes themeResId: Int, cancelable: Boolean) : Dialog(context, themeResId), DialogInterface {
    private val mAlert: DialogController


    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : this(context, 0) {

        setCancelable(cancelable)
        setOnCancelListener(cancelListener)
    }


    @JvmOverloads protected constructor(context: Context, @StyleRes themeResId: Int = 0) : this(context, themeResId, true) {}

    init {
        setCancelable(cancelable)

        mAlert = DialogController.create(getContext(), this, window)
    }


    fun getButton(whichButton: Int): Button? {
        return mAlert.getButton(whichButton)
    }


    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        mAlert.setTitle(title)
    }


    fun setMessage(message: CharSequence?) {
        mAlert.setMessage(message)
    }


    fun setButton(whichButton: Int, text: CharSequence, msg: Message) {
        mAlert.setButton(whichButton, text, null, msg)
    }


    fun setButton(whichButton: Int, text: CharSequence, listener: DialogInterface.OnClickListener) {
        mAlert.setButton(whichButton, text, listener, null)
    }

    fun setIcon(@DrawableRes resId: Int) {
        mAlert.setIcon(resId)
    }

    fun setIcon(icon: Drawable?) {
        mAlert.setIcon(icon)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAlert.installContent()
    }


    class Builder @JvmOverloads constructor(context: Context, themeResId: Int = 0) {
        private val P: DialogController.DialogParams

        val context: Context
            get() = P.mContext

        init {
            P = DialogController.DialogParams(context)
        }


        fun setTitle(@StringRes titleId: Int): Builder {
            P.mTitle = P.mContext.getText(titleId)
            return this
        }


        fun setTitle(title: CharSequence): Builder {
            P.mTitle = title
            return this
        }


        fun setMessage(@StringRes messageId: Int): Builder {
            P.mMessage = P.mContext.getText(messageId)
            return this
        }


        fun setMessage(message: CharSequence): Builder {
            P.mMessage = message
            return this
        }


        fun setIcon(@DrawableRes iconId: Int): Builder {
            P.mIconId = iconId
            return this
        }


        fun setIcon(icon: Drawable): Builder {
            P.mIcon = icon
            return this
        }


        fun setPositiveButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener): Builder {
            P.mPositiveButtonText = P.mContext.getText(textId)
            P.mPositiveButtonListener = listener
            return this
        }


        fun setPositiveButton(text: CharSequence, listener: DialogInterface.OnClickListener): Builder {
            P.mPositiveButtonText = text
            P.mPositiveButtonListener = listener
            return this
        }


        fun setNegativeButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener): Builder {
            P.mNegativeButtonText = P.mContext.getText(textId)
            P.mNegativeButtonListener = listener
            return this
        }


        fun setNegativeButton(text: CharSequence, listener: DialogInterface.OnClickListener): Builder {
            P.mNegativeButtonText = text
            P.mNegativeButtonListener = listener
            return this
        }


        fun setNeutralButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener): Builder {
            P.mNeutralButtonText = P.mContext.getText(textId)
            P.mNeutralButtonListener = listener
            return this
        }


        fun setNeutralButton(text: CharSequence, listener: DialogInterface.OnClickListener): Builder {
            P.mNeutralButtonText = text
            P.mNeutralButtonListener = listener
            return this
        }


        fun setCancelable(cancelable: Boolean): Builder {
            P.mCancelable = cancelable
            return this
        }


        fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): Builder {
            P.mOnCancelListener = onCancelListener
            return this
        }


        fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): Builder {
            P.mOnDismissListener = onDismissListener
            return this
        }


        fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener): Builder {
            P.mOnKeyListener = onKeyListener
            return this
        }


        fun create(): PopupDialog {
            // Context has already been wrapped with the appropriate theme.
            val dialog = PopupDialog(P.mContext, R.style.Theme_Dialog, false)
            P.apply(dialog.mAlert)
            dialog.setCancelable(P.mCancelable)
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true)
            }
            dialog.setOnCancelListener(P.mOnCancelListener)
            dialog.setOnDismissListener(P.mOnDismissListener)
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener)
            }
            dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            return dialog
        }


        fun show(): PopupDialog {
            val dialog = create()
            dialog.show()
            return dialog
        }
    }

    companion object {


        /**
         * No layout hint.
         *
         * @hide
         */
        val LAYOUT_HINT_NONE = 0

        /**
         * Hint layout to the side.
         *
         * @hide
         */
        val LAYOUT_HINT_SIDE = 1
    }

}