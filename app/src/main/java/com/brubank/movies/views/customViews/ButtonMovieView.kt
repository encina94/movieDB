package com.brubank.movies.views.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.brubank.movies.R

class ButtonMovieView: AppCompatButton{

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        stateListAnimator = null
        background = ContextCompat.getDrawable(context, R.drawable.oval_button)
        isAllCaps = false
    }
}