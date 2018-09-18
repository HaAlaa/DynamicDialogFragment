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
import android.view.Window
import android.widget.*

import com.example.asus.modalwindow.R

import java.lang.ref.WeakReference

class DialogController protected constructor(private val mContext: Context, private val mDialogInterface: DialogInterface, protected val mWindow: Window) {

    private var mTitle: CharSequence? = null
    protected var mMessage: CharSequence? = null

    private lateinit var mButtonPositive: LinearLayout
    private lateinit var mPositiveBtnText: TextView
    private var mButtonPositiveText: CharSequence? = null
    private var mButtonPositiveMessage: Message? = null

    private lateinit var mButtonNegative: Button
    private var mButtonNegativeText: CharSequence? = null
    private var mButtonNegativeMessage: Message? = null

    private lateinit var mButtonNeutral: LinearLayout
    private lateinit var mNeutralBtnText: TextView
    private var mButtonNeutralText: CharSequence? = null
    private var mButtonNeutralMessage: Message? = null


    private var mIconId = 0
    private var mIcon: Drawable? = null

    private lateinit var mIconView: ImageView
    private lateinit var mTitleView: TextView
    protected lateinit var mMessageView: TextView

    private val mHandler: Handler

    init {
        mHandler = ButtonHandler(mDialogInterface)

        /* We use a custom title so never request a window title */
        mWindow.requestFeature(Window.FEATURE_NO_TITLE)
    }

    private val mButtonHandler = View.OnClickListener { view ->
        val message: Message?
        if (view === mButtonPositive && mButtonPositiveMessage != null) {
            message = Message.obtain(mButtonPositiveMessage)
        } else if (view === mButtonNegative && mButtonNegativeMessage != null) {
            message = Message.obtain(mButtonNegativeMessage)
        } else if (view === mButtonNeutral && mButtonNeutralMessage != null) {
            message = Message.obtain(mButtonNeutralMessage)
        } else {
            message = null
        }

        message?.sendToTarget()

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
//        mTitleView?.text = title
    }

    fun setMessage(message: CharSequence?) {
        mMessage = message
//        mMessageView?.text = message
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

//        if (resId != 0) {
//            mIconView.visibility = View.VISIBLE
//            mIconView.setImageResource(mIconId)
//        } else {
//            mIconView.visibility = View.GONE
//        }
    }

    /**
     * Specifies the icon to display next to the alert title.
     *
     * @param icon the drawable to use as the icon or null for no icon
     */

    fun setIcon(icon: Drawable?) {
        mIcon = icon
        mIconId = 0

//        if (icon != null) {
//            mIconView.visibility = View.VISIBLE
//            mIconView.setImageDrawable(icon)
//        } else {
//            mIconView.visibility = View.GONE
//        }
    }

    private fun setupView() {
        val parentPanel = mWindow.findViewById<View>(R.id.dialogParent)
        mTitleView = mWindow.findViewById<View>(R.id.dialogTitleTV) as TextView
        mIconView = mWindow.findViewById<View>(R.id.dialogImageIV) as ImageView
        mMessageView = mWindow.findViewById<View>(R.id.dialogMessageTV) as TextView
        mButtonPositive = mWindow.findViewById<View>(R.id.positiveBtn) as LinearLayout
        mPositiveBtnText = mWindow.findViewById<View>(R.id.positiveBtnText) as TextView
        mButtonNegative = mWindow.findViewById<View>(R.id.negativeBtn) as Button
        mButtonNeutral = mWindow.findViewById<View>(R.id.neutralBtn) as LinearLayout
        mNeutralBtnText = mWindow.findViewById<View>(R.id.neutralBtnText) as TextView


        setupContent()
        setupButtons()
        setupTitle()
        setupImage()
    }


    protected fun setupTitle() {

        val hasTextTitle = !TextUtils.isEmpty(mTitle)
        if (hasTextTitle) {
            // Display the title if a title is supplied, else hide it.
            mTitleView.text = mTitle
        } else {
            mTitleView.visibility = View.GONE
        }
    }

    protected fun setupImage() {
        // Do this last so that if the user has supplied any icons we
        // use them instead of the default ones. If the user has
        // specified 0 then make it disappear.
        if (mIconId != 0) {
            mIconView.setImageResource(mIconId)
        } else if (mIcon != null) {
            mIconView.setImageDrawable(mIcon)
        } else {
            mIconView.visibility = View.GONE
        }
    }

    protected fun setupContent() {

        if (mMessage != null) {
            mMessageView.text = mMessage
        } else {
            mMessageView.visibility = View.GONE
        }
    }


    protected fun setupButtons() {
        val BIT_BUTTON_POSITIVE = 1
        val BIT_BUTTON_NEGATIVE = 2
        val BIT_BUTTON_NEUTRAL = 4
        var whichButtons = 0

        mButtonPositive.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonPositiveText)) {
            mButtonPositive.visibility = View.GONE
        } else {
            mPositiveBtnText.text = mButtonPositiveText
            mButtonPositive.visibility = View.VISIBLE
            whichButtons = whichButtons or BIT_BUTTON_POSITIVE
        }

        mButtonNegative.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonNegativeText)) {
            mButtonNegative.visibility = View.GONE
        } else {
            mButtonNegative.text = mButtonNegativeText
            mButtonNegative.visibility = View.VISIBLE

            whichButtons = whichButtons or BIT_BUTTON_NEGATIVE
        }


        mButtonNeutral.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonNeutralText)) {
            mButtonNeutral.visibility = View.GONE
        } else {
            mNeutralBtnText.text = mButtonNeutralText
            mButtonNeutral.visibility = View.VISIBLE

            whichButtons = whichButtons or BIT_BUTTON_NEUTRAL
        }

    }


    fun getIconAttributeResId(attrId: Int): Int {
        val out = TypedValue()
        mContext.theme.resolveAttribute(attrId, out, true)
        return out.resourceId
    }


    fun getButton(whichButton: Int): View? {
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
    }
}