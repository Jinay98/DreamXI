package com.example.android.dreamxi

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnadd = findViewById(R.id.button) as Button
        val btnview =findViewById(R.id.button3) as Button
        val b1 =findViewById(R.id.b1) as Button
        val b2 =findViewById(R.id.b2) as Button
        val b3 =findViewById(R.id.b3) as Button
        val b4 =findViewById(R.id.b4) as Button
        val b5 =findViewById(R.id.b5) as Button
        val b6 =findViewById(R.id.b6) as Button
        val b7 =findViewById(R.id.b7) as Button
        val b8 =findViewById(R.id.b8) as Button
        val b9 =findViewById(R.id.b9) as Button
        val b10 =findViewById(R.id.b10) as Button
        val b11 =findViewById(R.id.b11) as Button
        val b12 =findViewById(R.id.b12) as Button
        val b13 =findViewById(R.id.b13) as Button
        val b14 =findViewById(R.id.b14) as Button
        Timer().schedule(1000){
            //do something
            colourchangeRed(b1)
            colourchangeRed(b5)
            colourchangeRed(b6)
        }
        Timer().schedule(1500){
            //do something
            colourchangeLight(b1)
            colourchangeLight(b6)
            colourchangeRed(b7)
            colourchangeLight(b5)
            colourchangeRed(b12)
            colourchangeRed(b10)
        }
        Timer().schedule(2000){
            //do something
            colourchangeLight(b12)
            colourchangeLight(b7)
            colourchangeRed(b8)
            colourchangeLight(b10)
            colourchangeRed(b2)
        }
        Timer().schedule(2500){
            //do something
            colourchangeLight(b2)
            colourchangeLight(b8)
            colourchangeRed(b13)
            colourchangeRed(b9)
            colourchangeRed(b11)
        }
        Timer().schedule(3000){
            //do something
            colourchangeLight(b9)
            colourchangeLight(b11)
            colourchangeLight(b13)
            colourchangeRed(b14)
            colourchangeRed(b4)
            colourchangeRed(b3)
        }
        Timer().schedule(3500){
            //do something
            colourchangeLight(b3)
            colourchangeLight(b4)
            colourchangeLight(b14)

        }








        btnadd!!.setOnClickListener {
            colourchangeDark(btnadd)
            btnadd.setTextColor(Color.parseColor("#ff0000"))

            val intent = Intent(this@MainActivity, AddActivity::class.java)
            println("Hi")
            startActivity(intent)
        }
        btnview!!.setOnClickListener {
            colourchangeDark(btnview)
            btnview.setTextColor(Color.parseColor("#ff0000"))

            val intent = Intent(this@MainActivity, ViewActivity::class.java)
            println("Hi2")
            startActivity(intent)
        }

    }

    fun colourchangeDark(b: Button) {
        b.setBackgroundDrawable(resources.getDrawable(R.drawable.roundedbuttondark))
    }
    fun colourchangeLight(b: Button) {
        b.setBackgroundDrawable(resources.getDrawable(R.drawable.circlebutton))
    }
    fun colourchangeRed(b: Button) {
        b.setBackgroundDrawable(resources.getDrawable(R.drawable.circlebuttonred))
    }


}
