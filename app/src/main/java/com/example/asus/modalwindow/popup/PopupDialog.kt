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
import android.view.View

import com.example.asus.modalwindow.R

class PopupDialog internal constructor(context: Context, @StyleRes themeResId: Int, cancelable: Boolean) : Dialog(context, themeResId), DialogInterface {
    private val mDialogController: DialogController


    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : this(context, 0) {

        setCancelable(cancelable)
        setOnCancelListener(cancelListener)
    }


    @JvmOverloads protected constructor(context: Context, @StyleRes themeResId: Int = 0) : this(context, themeResId, true) {}

    init {
        setCancelable(cancelable)

        mDialogController = DialogController.create(getContext(), this, window)
    }


    fun getButton(whichButton: Int): View? {
        return mDialogController.getButton(whichButton)
    }


    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        mDialogController.setTitle(title)
    }


    fun setMessage(message: CharSequence?) {
        mDialogController.setMessage(message)
    }


    fun setButton(whichButton: Int, text: CharSequence, msg: Message) {
        mDialogController.setButton(whichButton, text, null, msg)
    }


    fun setButton(whichButton: Int, text: CharSequence, listener: DialogInterface.OnClickListener) {
        mDialogController.setButton(whichButton, text, listener, null)
    }

    fun setIcon(@DrawableRes resId: Int) {
        mDialogController.setIcon(resId)
    }

    fun setIcon(icon: Drawable?) {
        mDialogController.setIcon(icon)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogController.installContent()
    }


    class Builder @JvmOverloads constructor(context: Context, themeResId: Int = 0) {
        private val dialogParams: DialogController.DialogParams

        val context: Context
            get() = dialogParams.mContext

        init {
            dialogParams = DialogController.DialogParams(context)
        }


        fun setTitle(@StringRes titleId: Int): Builder {
            dialogParams.mTitle = dialogParams.mContext.getText(titleId)
            return this
        }


        fun setTitle(title: CharSequence): Builder {
            dialogParams.mTitle = title
            return this
        }


        fun setMessage(@StringRes messageId: Int): Builder {
            dialogParams.mMessage = dialogParams.mContext.getText(messageId)
            return this
        }


        fun setMessage(message: CharSequence): Builder {
            dialogParams.mMessage = message
            return this
        }


        fun setIcon(@DrawableRes iconId: Int): Builder {
            dialogParams.mIconId = iconId
            return this
        }


        fun setIcon(icon: Drawable): Builder {
            dialogParams.mIcon = icon
            return this
        }


        fun setPositiveButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener): Builder {
            dialogParams.mPositiveButtonText = dialogParams.mContext.getText(textId)
            dialogParams.mPositiveButtonListener = listener
            return this
        }


        fun setPositiveButton(text: CharSequence, listener: DialogInterface.OnClickListener): Builder {
            dialogParams.mPositiveButtonText = text
            dialogParams.mPositiveButtonListener = listener
            return this
        }


        fun setNegativeButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener): Builder {
            dialogParams.mNegativeButtonText = dialogParams.mContext.getText(textId)
            dialogParams.mNegativeButtonListener = listener
            return this
        }


        fun setNegativeButton(text: CharSequence, listener: DialogInterface.OnClickListener): Builder {
            dialogParams.mNegativeButtonText = text
            dialogParams.mNegativeButtonListener = listener
            return this
        }


        fun setNeutralButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener): Builder {
            dialogParams.mNeutralButtonText = dialogParams.mContext.getText(textId)
            dialogParams.mNeutralButtonListener = listener
            return this
        }


        fun setNeutralButton(text: CharSequence, listener: DialogInterface.OnClickListener): Builder {
            dialogParams.mNeutralButtonText = text
            dialogParams.mNeutralButtonListener = listener
            return this
        }


        fun setCancelable(cancelable: Boolean): Builder {
            dialogParams.mCancelable = cancelable
            return this
        }


        fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): Builder {
            dialogParams.mOnCancelListener = onCancelListener
            return this
        }


        fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): Builder {
            dialogParams.mOnDismissListener = onDismissListener
            return this
        }


        fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener): Builder {
            dialogParams.mOnKeyListener = onKeyListener
            return this
        }


        fun create(): PopupDialog {
            // Context has already been wrapped with the appropriate theme.
            val dialog = PopupDialog(dialogParams.mContext, R.style.Theme_Dialog, false)
            dialogParams.apply(dialog.mDialogController)
            dialog.setCancelable(dialogParams.mCancelable)
            if (dialogParams.mCancelable) {
                dialog.setCanceledOnTouchOutside(true)
            }
            dialog.setOnCancelListener(dialogParams.mOnCancelListener)
            dialog.setOnDismissListener(dialogParams.mOnDismissListener)
            if (dialogParams.mOnKeyListener != null) {
                dialog.setOnKeyListener(dialogParams.mOnKeyListener)
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