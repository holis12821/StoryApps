package com.example.storyapps.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.storyapps.R

class PasswordEditText : AppCompatEditText, View.OnTouchListener {

    private var roundedBackground: Drawable? = null
    private var textHint: String? = null
    private var textHintColor: Int? = null
    private var sizeText: Float? = null
    private var colorText: Int? = null
    private var isHidden = true
    private val passwordTransformationMethod = PasswordTransformationMethod.getInstance()
    private lateinit var eyesIcon: Drawable

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        eyesIcon = ContextCompat.getDrawable(context, R.drawable.ic_eye_off) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(text: Editable?) {
                text?.let {
                    if (it.length < 6 && it.isNotBlank()) {
                        error = context.getString(R.string.password_less_than_6)
                    }
                }
            }
        })
        setupCustomFont(context, attrs, defStyleAttr)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = roundedBackground
        transformationMethod = if (isHidden) {
            showPasswordVisible()
            passwordTransformationMethod
        } else {
            showPasswordHidden()
            null
        }
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        hint = textHint
        setHintTextColor(textHintColor ?: ContextCompat.getColor(context, R.color.grey))
        textSize = sizeText ?: 0F
        setTextColor(colorText ?: ContextCompat.getColor(context, R.color.dark_blue_500))
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun setupCustomFont(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        if (!isInEditMode) {
            val typedArray =
                context?.obtainStyledAttributes(attrs, R.styleable.PasswordEditText, defStyleAttr, 0)
            var typeIndex = typedArray?.getInt(R.styleable.PasswordEditText_passwordEditTextViewFontFamily, 0)
            typedArray?.recycle()
            val fontType = listOf(
                "titillium_web_bold.ttf",
                "titillium_web_light.ttf",
                "titillium_web_regular.ttf",
                "titillium_web_semi_bold.ttf",
                "titillium_web_italic.ttf"
            )
            if (typeIndex != null) {
                if (typeIndex >= fontType.size) typeIndex = fontType.size - 1
                val mFontPath = "fonts/${fontType[typeIndex]}"
                val mTypeFace = Typeface.createFromAsset(context?.assets, mFontPath)
                typeface = mTypeFace
            }
        }
    }

    private fun setDrawables(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom)
    }

    private fun showPasswordVisible() {
        setDrawables(end = eyesIcon)
    }

    private fun showPasswordHidden() {
        setDrawables(end = eyesIcon)
    }

    fun setRoundedBackground(roundedBackground: Drawable) {
        this.roundedBackground = roundedBackground
    }

    fun setTextHint(txtHint: String) {
        textHint = txtHint
    }

    fun setTextHintColor(textHintColor: Int?) {
        this.textHintColor = textHintColor
    }

    fun setSizeText(size: Float) {
        sizeText = size
    }

    fun setColorText(colorText: Int?) {
        this.colorText = colorText
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (compoundDrawables[2] != null) {
            val toggleButtonStart: Float
            val toggleButtonEnd: Float
            var isToggleButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                toggleButtonEnd = (eyesIcon.intrinsicWidth.plus(paddingStart)).toFloat()
                if((event?.x ?: 0.0F) < toggleButtonEnd) isToggleButtonClicked = true
            } else {
                toggleButtonStart = (width - paddingEnd - eyesIcon.intrinsicWidth).toFloat()
                if ((event?.x ?: 0.0F) > toggleButtonStart) isToggleButtonClicked = true
            }

            return if (isToggleButtonClicked) {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        eyesIcon = ContextCompat.getDrawable(context, R.drawable.ic_eye_open) as Drawable
                        showPasswordVisible()
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        eyesIcon = ContextCompat.getDrawable(context, R.drawable.ic_eye_off) as Drawable
                        isHidden = !isHidden
                        true
                    }
                    else -> false
                }
            } else false
        }
        return false
    }

}