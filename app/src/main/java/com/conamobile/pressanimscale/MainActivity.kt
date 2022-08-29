package com.conamobile.pressanimscale

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.conamobile.pressanimscale.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {
        binding.button.animateClick {
            Toast.makeText(this, "CLick", Toast.LENGTH_SHORT).show()
        }
    }

    inline fun View.animateClick(crossinline clickListener: (View) -> Unit) {
        setOnTouchListener(
            object : View.OnTouchListener {
                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {
                    val action = motionEvent.action
                    if (action == MotionEvent.ACTION_DOWN) {
                        v.animate().scaleXBy(-0.2f).setDuration(200).start()
                        v.animate().scaleYBy(-0.2f).setDuration(200).start()
                        return true
                    } else if (action == MotionEvent.ACTION_UP) {
                        clickListener(this@animateClick)
                        v.animate().cancel()
                        v.animate().scaleX(1f).setDuration(1000).start()
                        v.animate().scaleY(1f).setDuration(1000).start()
                        return true
                    }
                    return false
                }
            })
    }
}