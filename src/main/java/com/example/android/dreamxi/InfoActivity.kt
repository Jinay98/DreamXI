package com.example.android.dreamxi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val name=findViewById<TextView>(R.id.name)
        val pos =findViewById<TextView>(R.id.positiontv)
        val age = findViewById<TextView>(R.id.agetv)
        val batpts = findViewById<TextView>(R.id.batptstv)
        val bowlpts = findViewById<TextView>(R.id.bowlptstv)
        val allpts = findViewById<TextView>(R.id.allptstv)
        val imageView= findViewById<ImageView>(R.id.user_profile_photo)
        println("Age is ="+intent.getLongExtra("Age",18))

        name.setText(intent.getStringExtra("Name"))
        pos.setText(intent.getStringExtra("Position"))
        age.setText(intent.getLongExtra("age",18).toString())
        batpts.setText(intent.getLongExtra("Batpts",50).toString())
        bowlpts.setText(intent.getLongExtra("Bowlpts",50).toString())
        allpts.setText(intent.getLongExtra("Allpts",50).toString())
        Picasso.get().load(intent.getStringExtra("URL")).resize(175, 175).centerCrop().into(imageView)



    }
}
