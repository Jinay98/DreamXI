package com.example.android.dreamxi

import android.icu.util.ULocale
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_layout.view.*
import android.content.Intent
import android.widget.AdapterView
import android.widget.Toast
import com.example.android.dreamxi.R.id.listView
import kotlinx.android.synthetic.main.activity_info.*


class ViewActivity : AppCompatActivity() {
    lateinit var mRecyclerView : ListView
    lateinit var ref:DatabaseReference
    lateinit var playerList:MutableList<Player>
    //lateinit var mDatabase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        mRecyclerView = findViewById(R.id.listView)
        playerList= mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Players")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    playerList.clear()
                    for(h in p0.children)
                    {
                        val player=h.getValue(Player::class.java)
                        playerList.add(player!! )

                        //playerList.sortBy { player.name  }
                        playerList.sortWith(object: Comparator<Player>{
                            override fun compare(p1: Player, p2: Player): Int = when {
                                (p1.batpts+p1.bowlpts+p1.allpts)> (p2.batpts+p2.bowlpts+p2.allpts) -> -1
                                (p1.batpts+p1.bowlpts+p1.allpts) == (p2.batpts+p2.bowlpts+p2.allpts) -> 0
                                else -> 1
                            }
                        })




                    }
                    val adapter=PlayerAdapter(applicationContext,R.layout.list_layout,playerList)
                    mRecyclerView.adapter=adapter
                }
            }

        })


            mRecyclerView.setOnItemClickListener { parent, view, position, id ->
                //Toast.makeText(applicationContext,"age is = "+playerList[position].age,Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ViewActivity, InfoActivity::class.java).putExtra("URL",playerList[position].url)
                intent.putExtra("Name",playerList[position].name)
                intent.putExtra("age",playerList[position].age)
                intent.putExtra("Position",playerList[position].pos)

                intent.putExtra("Batpts",playerList[position].batpts)
                intent.putExtra("Bowlpts",playerList[position].bowlpts)
                intent.putExtra("Allpts",playerList[position].allpts)
                println("Hi")
                startActivity(intent)
            }








    }


}
