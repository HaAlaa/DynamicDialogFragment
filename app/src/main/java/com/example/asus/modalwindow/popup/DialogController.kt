package com.example.asus.modalwindow.popup

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.example.asus.modalwindow.R

import java.lang.ref.WeakReference

class DialogController protected constructor(private val mContext: Context, private val mDialogInterface: DialogInterface, protected val mWindow: Window) {

    private var mTitle: CharSequence? = null
    protected var mMessage: CharSequence? = null

    private var mButtonPositive: Button? = null
    private var mButtonPositiveText: CharSequence? = null
    private var mButtonPositiveMessage: Message? = null

    private var mButtonNegative: Button? = null
    private var mButtonNegativeText: CharSequence? = null
    private var mButtonNegativeMessage: Message? = null

    private var mButtonNeutral: Button? = null
    private var mButtonNeutralText: CharSequence? = null
    private var mButtonNeutralMessage: Message? = null


    private var mIconId = 0
    private var mIcon: Drawable? = null

    private var mIconView: ImageView? = null
    private var mTitleView: TextView? = null
    protected var mMessageView: TextView? = null

    private val mHandler: Handler
    init {
        mHandler = ButtonHandler(mDialogInterface)

        /* We use a custom title so never request a window title */
        mWindow.requestFeature(Window.FEATURE_NO_TITLE)
    }
    private val mButtonHandler = View.OnClickListener { v ->
        val m: Message?
        if (v === mButtonPositive && mButtonPositiveMessage != null) {
            m = Message.obtain(mButtonPositiveMessage)
        } else if (v === mButtonNegative && mButtonNegativeMessage != null) {
            m = Message.obtain(mButtonNegativeMessage)
        } else if (v === mButtonNeutral && mButtonNeutralMessage != null) {
            m = Message.obtain(mButtonNeutralMessage)
        } else {
            m = null
        }

        m?.sendToTarget()

        // Post a message so we dismiss after the above handlers are executed
        mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialogInterface)
                .sendToTarget()
    }

    private class ButtonHandler(dialog: DialogInterface) : Handler() {

        private val mDialog: WeakReference<DialogInterface>

        init {
            mDialog = WeakReference(dialog)
        }

        override fun handleMessage(msg: Message) {
            when (msg.what) {

                DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL -> (msg.obj as DialogInterface.OnClickListener).onClick(mDialog.get(), msg.what)

                MSG_DISMISS_DIALOG -> (msg.obj as DialogInterface).dismiss()
            }
        }

        companion object {
            // Button clicks have Message.what as the BUTTON{1,2,3} constant
            val MSG_DISMISS_DIALOG = 1
        }
    }




    fun installContent(params: DialogParams) {
        params.apply(this)
        installContent()
    }


    fun installContent() {
        val contentView = R.layout.dialog_popup
        mWindow.setContentView(contentView)
        setupView()
    }


    fun setTitle(title: CharSequence?) {
        mTitle = title
        if (mTitleView != null) {
            mTitleView!!.text = title
        }
    }

    fun setMessage(message: CharSequence?) {
        mMessage = message
        if (mMessageView != null) {
            mMessageView!!.text = message
        }
    }

    fun setButton(whichButton: Int, text: CharSequence,
                  listener: DialogInterface.OnClickListener?, msg: Message?) {
        var msg = msg

        if (msg == null && listener != null) {
            msg = mHandler.obtainMessage(whichButton, listener)
        }

        when (whichButton) {

            DialogInterface.BUTTON_POSITIVE -> {
                mButtonPositiveText = text
                mButtonPositiveMessage = msg
            }

            DialogInterface.BUTTON_NEGATIVE -> {
                mButtonNegativeText = text
                mButtonNegativeMessage = msg
            }

            DialogInterface.BUTTON_NEUTRAL -> {
                mButtonNeutralText = text
                mButtonNeutralMessage = msg
            }

            else -> throw IllegalArgumentException("Button does not exist")
        }
    }

    fun setIcon(resId: Int) {
        mIcon = null
        mIconId = resId

        if (mIconView != null) {
            if (resId != 0) {
                mIconView!!.visibility = View.VISIBLE
                mIconView!!.setImageResource(mIconId)
            } else {
                mIconView!!.visibility = View.GONE
            }
        }
    }

    /**
     * Specifies the icon to display next to the alert title.
     *
     * @param icon the drawable to use as the icon or null for no icon
     */

    fun setIcon(icon: Drawable?) {
        mIcon = icon
        mIconId = 0

        if (mIconView != null) {
            if (icon != null) {
                mIconView!!.visibility = View.VISIBLE
                mIconView!!.setImageDrawable(icon)
            } else {
                mIconView!!.visibility = View.GONE
            }
        }
    }

    private fun setupView() {
        val parentPanel = mWindow.findViewById<View>(R.id.dialogParent)
        setupContent(parentPanel)
        setupButtons(parentPanel)
        setupTitle(parentPanel)
        setupImage(parentPanel)
    }


    protected fun setupTitle(topPanel: View) {

        val hasTextTitle = !TextUtils.isEmpty(mTitle)
        if (hasTextTitle) {
            // Display the title if a title is supplied, else hide it.
            mTitleView = mWindow.findViewById<View>(R.id.dialogTitleTV) as TextView
            mTitleView!!.text = mTitle
        } else {
            mTitleView!!.visibility = View.GONE
        }
    }

    protected fun setupImage(topPanel: View) {
        mIconView = mWindow.findViewById<View>(R.id.dialogImageIV) as ImageView
        // Do this last so that if the user has supplied any icons we
        // use them instead of the default ones. If the user has
        // specified 0 then make it disappear.
        if (mIconId != 0) {
            mIconView!!.setImageResource(mIconId)
        } else if (mIcon != null) {
            mIconView!!.setImageDrawable(mIcon)
        } else {
            mIconView!!.visibility = View.GONE
        }
    }

    protected fun setupContent(contentPanel: View) {
        // Special case for users that only want to display a String
        mMessageView = contentPanel.findViewById<View>(R.id.dialogMessageTV) as TextView
        if (mMessageView == null) {
            return
        }
        if (mMessage != null) {
            mMessageView!!.text = mMessage
        } else {
            mMessageView!!.visibility = View.GONE
        }
    }


    protected fun setupButtons(buttonPanel: View) {
        val BIT_BUTTON_POSITIVE = 1
        val BIT_BUTTON_NEGATIVE = 2
        val BIT_BUTTON_NEUTRAL = 4
        var whichButtons = 0
        mButtonPositive = buttonPanel.findViewById<View>(R.id.positiveBtn) as Button
        mButtonPositive!!.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonPositiveText)) {
            mButtonPositive!!.visibility = View.GONE
        } else {
            mButtonPositive!!.text = mButtonPositiveText
            mButtonPositive!!.visibility = View.VISIBLE
            whichButtons = whichButtons or BIT_BUTTON_POSITIVE
        }

        mButtonNegative = buttonPanel.findViewById<View>(R.id.negativeBtn) as Button
        mButtonNegative!!.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonNegativeText)) {
            mButtonNegative!!.visibility = View.GONE
        } else {
            mButtonNegative!!.text = mButtonNegativeText
            mButtonNegative!!.visibility = View.VISIBLE

            whichButtons = whichButtons or BIT_BUTTON_NEGATIVE
        }

        mButtonNeutral = buttonPanel.findViewById<View>(R.id.neutralBtn) as Button
        mButtonNeutral!!.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonNeutralText)) {
            mButtonNeutral!!.visibility = View.GONE
        } else {
            mButtonNeutral!!.text = mButtonNeutralText
            mButtonNeutral!!.visibility = View.VISIBLE

            whichButtons = whichButtons or BIT_BUTTON_NEUTRAL
        }

    }


    fun getIconAttributeResId(attrId: Int): Int {
        val out = TypedValue()
        mContext.theme.resolveAttribute(attrId, out, true)
        return out.resourceId
    }


    fun getButton(whichButton: Int): Button? {
        when (whichButton) {
            DialogInterface.BUTTON_POSITIVE -> return mButtonPositive
            DialogInterface.BUTTON_NEGATIVE -> return mButtonNegative
            DialogInterface.BUTTON_NEUTRAL -> return mButtonNeutral
            else -> return null
        }
    }


    class DialogParams(val mContext: Context) {

        val mInflater: LayoutInflater


        var mIconId = 0

        var mIcon: Drawable? = null

        var mTitle: CharSequence? = null

        var mMessage: CharSequence? = null

        var mPositiveButtonText: CharSequence? = null

        var mPositiveButtonListener: DialogInterface.OnClickListener? = null

        var mNegativeButtonText: CharSequence? = null

        var mNegativeButtonListener: DialogInterface.OnClickListener? = null

        var mNeutralButtonText: CharSequence? = null

        var mNeutralButtonListener: DialogInterface.OnClickListener? = null

        var mCancelable: Boolean = false

        var mOnCancelListener: DialogInterface.OnCancelListener? = null

        var mOnDismissListener: DialogInterface.OnDismissListener? = null

        var mOnKeyListener: DialogInterface.OnKeyListener? = null

        var mOnClickListener: DialogInterface.OnClickListener? = null

        init {
            mCancelable = true
            mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }


        fun apply(dialog: DialogController) {
            if (mTitle != null) {
                dialog.setTitle(mTitle!!)
            }
            if (mIcon != null) {
                dialog.setIcon(mIcon)
            }
            if (mIconId != 0) {
                dialog.setIcon(mIconId)
            }
            if (mMessage != null) {
                dialog.setMessage(mMessage!!)
            }
            if (mPositiveButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, mPositiveButtonText!!,
                        mPositiveButtonListener, null)
            }
            if (mNegativeButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText!!,
                        mNegativeButtonListener, null)
            }
            if (mNeutralButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, mNeutralButtonText!!,
                        mNeutralButtonListener, null)
            }

        }
    }

    companion object {


        fun create(context: Context, di: DialogInterface, window: Window): DialogController {
            return DialogController(context, di, window)
        }

        internal fun canTextInput(v: View): Boolean {
            var v = v
            if (v.onCheckIsTextEditor()) {
                return true
            }

            if (v !is ViewGroup) {
                return false
            }

            val vg = v
            var i = vg.childCount
            while (i > 0) {
                i--
                v = vg.getChildAt(i)
                if (canTextInput(v)) {
                    return true
                }
            }

            return false
        }
    }
}