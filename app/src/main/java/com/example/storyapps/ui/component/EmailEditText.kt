package com.example.storyapps.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.storyapps.R

class EmailEditText : AppCompatEditText {

    private var roundedBackground: Drawable? = null
    private var colorText: Int? = null
    private var textHint: String? = null
    private var textHintColor: Int? = null
    private var sizeText: Float? = null
    private var textObserver: TextObserver? = null

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
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(text: Editable?) {
                textObserver?.textChangeCallback(text)
            }

        })
        setupCustomFont(context, attrs, defStyleAttr)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        background = roundedBackground
        setTextColor(colorText ?: ContextCompat.getColor(context, R.color.dark_blue_500))
        hint = textHint
        setHintTextColor(textHintColor ?: ContextCompat.getColor(context, R.color.grey))
        textSize = sizeText ?: 0F
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun setupCustomFont(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        if (!isInEditMode) {
            val typedArray =
                context?.obtainStyledAttributes(attrs, R.styleable.EmailEditText, defStyleAttr, 0)
            var typeIndex = typedArray?.getInt(R.styleable.EmailEditText_emailTextViewFontFamily, 0)
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

    fun setRoundedBackground(roundedBackground: Drawable) {
        this.roundedBackground = roundedBackground
    }

    fun setColorText(colorText: Int?) {
        this.colorText = colorText
    }

    fun setTextHint(textHint: String) {
        this.textHint = textHint
    }

    fun setTextHintColor(textHintColor: Int?) {
        this.textHintColor = textHintColor
    }

    fun setSizeText(size: Float) {
        sizeText = size
    }

    fun onTextListener(textObserver: TextObserver) {
        this.textObserver = textObserver
    }
}