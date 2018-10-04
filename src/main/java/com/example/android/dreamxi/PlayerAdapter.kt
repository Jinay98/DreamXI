package com.example.android.dreamxi

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PlayerAdapter(val mCtx:Context,val layoutResId:Int,val playerList:List<Player>):ArrayAdapter<Player>(mCtx,layoutResId,playerList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater= LayoutInflater.from(mCtx);
        val view:View =layoutInflater.inflate(layoutResId,null)
        val textViewName =view.findViewById<TextView>(R.id.playerName);
        val textViewPos = view.findViewById<TextView>(R.id.playerPos);
        val imageView = view.findViewById<ImageView>(R.id.imageView);
        val player = playerList[position]
        textViewName.text=player.name
        textViewPos.text=player.pos

        println("The age is = "+player.age)
        println("The batting is = "+player.batpts)
        println("the c Url is = "+"\""+player.url+"\"")
        Picasso.get().load(player.url).resize(250, 250).centerCrop().into(imageView)
        return view;
    }
}