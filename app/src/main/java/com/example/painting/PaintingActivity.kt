package com.example.painting

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.painting.Draw.PaintView.Companion.colorList
import com.example.painting.Draw.PaintView.Companion.currenBrush
import com.example.painting.Draw.PaintView.Companion.pathlist

class PaintingActivity : AppCompatActivity() {
    private val btnDen: ImageView by lazy { findViewById<ImageView>(R.id.btn_den) }
    private val btnTim: ImageView by lazy { findViewById<ImageView>(R.id.btn_tim) }
    private val btnVang: ImageView by lazy { findViewById<ImageView>(R.id.btn_vang) }
    private val btnXanh: ImageView by lazy { findViewById<ImageView>(R.id.btn_xanh) }
    private val btnDo: ImageView by lazy { findViewById<ImageView>(R.id.btn_do) }
    private val btnNew: ImageView by lazy { findViewById<ImageView>(R.id.btn_new) }
    private val btnBuy: ImageView by lazy { findViewById<ImageView>(R.id.btn_buy) }


    companion object {
        var path = Path()
        var paintBrusd = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_painting)
        supportActionBar?.hide()
        btnBuy.setOnClickListener {
            var intent = Intent(this, BilingActivity::class.java)
            startActivity(intent)
        }


        /////

        btnDen.setOnClickListener {
            paintBrusd.color = Color.parseColor("#5800FF")
            curentcolor(paintBrusd.color)
            // Toast.makeText(this,"Alo Màu Đen",Toast.LENGTH_SHORT).show()
        }

        btnTim.setOnClickListener {
            paintBrusd.color = Color.parseColor("#ff0000")
            curentcolor(paintBrusd.color)

        }

        btnVang.setOnClickListener {
            paintBrusd.color = Color.parseColor("#FBB454")
            curentcolor(paintBrusd.color)
        }

        btnXanh.setOnClickListener {
            paintBrusd.color = Color.parseColor("#748DA6")
            curentcolor(paintBrusd.color)
        }

        btnDo.setOnClickListener {
            paintBrusd.color = Color.parseColor("#14C38E")
            curentcolor(paintBrusd.color)
        }

        btnNew.setOnClickListener {
            pathlist.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun curentcolor(color: Int) {
        currenBrush = color
        path = Path()
    }
}