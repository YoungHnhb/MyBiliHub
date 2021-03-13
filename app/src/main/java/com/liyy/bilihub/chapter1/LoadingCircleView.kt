package com.liyy.bilihub.chapter1

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.liyy.bilihub.R
import kotlin.math.min

class LoadingCircleView : View {

    private lateinit var startAngleAnimator: ObjectAnimator
    private var outerStrokeWidth = 2f

    var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var startAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    var mSweepAngle = 0f
        get() = if (field < 5) {
            5f
        } else {
            field
        }
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        0
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingCircleView)
        outerStrokeWidth = typeArray.getDimension(R.styleable.LoadingCircleView_outer_stroke_width, 20f)

        typeArray.recycle()

        startAngleAnimator = ObjectAnimator.ofFloat(this, "startAngle", 360f)
        startAngleAnimator.repeatCount = ObjectAnimator.INFINITE
        startAngleAnimator.duration = 1500
        startAngleAnimator.interpolator = LinearInterpolator()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPaint.strokeWidth = outerStrokeWidth
        mPaint.color = Color.LTGRAY
        mPaint.style = Paint.Style.STROKE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAngleAnimator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        startAngleAnimator.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(
            outerStrokeWidth, outerStrokeWidth,
            width.toFloat() - outerStrokeWidth,
            height.toFloat() - outerStrokeWidth,
            startAngle, mSweepAngle, false, mPaint
        )
    }
}