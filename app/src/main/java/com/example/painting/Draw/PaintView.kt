package com.example.painting.Draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.painting.PaintingActivity.Companion.paintBrusd
import com.example.painting.PaintingActivity.Companion.path

class PaintView: View {
    var params : ViewGroup.LayoutParams? =null

    companion object{
        var pathlist= ArrayList<Path>()
        var colorList= ArrayList<Int>()
        var currenBrush=Color.BLACK
    }
    constructor(context: Context) : this(context, null){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    private fun init(){
        paintBrusd.isAntiAlias= true
        paintBrusd.color = currenBrush
        paintBrusd.style= Paint.Style.STROKE
        paintBrusd.strokeJoin=Paint.Join.ROUND
        paintBrusd.strokeWidth=6f;

        params=ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x=event!!.x
        var y=event!!.y

        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                path.moveTo(x,y)
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                path.lineTo(x,y)
                pathlist.add(path)
                colorList.add(currenBrush)
            }
            else -> return false
        }
        postInvalidate()
        return false;
    }

    override fun onDraw(canvas: Canvas) {
        for (i in pathlist.indices){
            paintBrusd.setColor(colorList[i])
            canvas.drawPath(pathlist[i], paintBrusd)
            invalidate()
        }
    }
}