package com.example.storyapps.ui.component

import android.text.Editable

interface TextObserver {
    fun textChangeCallback(text: Editable?)
}