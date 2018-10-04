package com.example.android.dreamxi

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.LiveFolders.INTENT
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add.*
import java.io.IOException
import java.util.*
import kotlin.concurrent.schedule

class AddActivity : AppCompatActivity(), View.OnClickListener {
    private val PICK_IMAGE_REQUEST=1234

    lateinit var finalurl:Uri
    lateinit var pname: EditText
    lateinit var page: EditText
    lateinit var ppos: EditText
    lateinit var batpts: EditText
    lateinit var bowlpts: EditText
    lateinit var allpts: EditText
    lateinit var addplayer: Button
    var filepath:Uri? = null
    var storage: FirebaseStorage?=null
    var storageReference: StorageReference?=null
    var count:Int = 0
    override fun onClick(p0: View) {
        if(p0===btnChoose)
        {
            colourchangeDark(btnChoose)
            btnChoose.setTextColor(Color.parseColor("#ff0000"))
            Timer().schedule(200000){
                //do something
                colourchangeLight(btnChoose)
                btnChoose.setTextColor(Color.parseColor("#ffffff"))
            }
            showFileChooser()
        }
        else if(p0===btnUpload)
        {
            count+=1
            colourchangeDark(btnUpload)
            btnUpload.setTextColor(Color.parseColor("#ff0000"))
            Timer().schedule(200000){
                //do something
                colourchangeLight(btnUpload)
                btnUpload.setTextColor(Color.parseColor("#ffffff"))
            }
            uploadFile()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== Activity.RESULT_OK&&data != null && data.data!=null)
        {
            filepath=data.data;
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
                imageView!!.setImageBitmap(bitmap)
            }catch (e: IOException){
            e.printStackTrace()
        }

        }
    }
    private fun uploadFile() {
        if(filepath!=null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading Image ... ")
            progressDialog.show()

            val imageRef=storageReference!!.child("images/"+UUID.randomUUID().toString())
            val addOnProgressListener = imageRef.putFile(filepath!!)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        println("Success")
                        Toast.makeText(applicationContext, "File Uploaded!", Toast.LENGTH_SHORT).show()
                        imageRef.downloadUrl.addOnCompleteListener () { taskSnapshot ->

                            var url = taskSnapshot.result
                            println("url =" + url.toString())
                            //Toast.makeText(applicationContext, "Url is ="+url, Toast.LENGTH_SHORT).show()

                            finalurl=url as Uri
                        }

                        }
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, "File Upload Failed!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                    }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECT IMAGE"),PICK_IMAGE_REQUEST)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        pname =findViewById(R.id.editTextplayerName)
        page = findViewById(R.id.editTextage)
        ppos = findViewById(R.id.editTextposition)
        batpts = findViewById(R.id.editTextbattingpts)
        bowlpts = findViewById(R.id.editTextbowlingpts)
        allpts = findViewById(R.id.editTextallrounderpts)
        addplayer = findViewById(R.id.addbtn)
        storage= FirebaseStorage.getInstance()
        storageReference=storage!!.reference
        btnChoose.setOnClickListener(this)
        btnUpload.setOnClickListener(this)
        addplayer.setOnClickListener{

            playerinsertion()
        }
    }
    public fun playerinsertion(){
        if(count ==0)
        {
            Toast.makeText(applicationContext,"Player Image Not Uploaded!!",Toast.LENGTH_SHORT).show()
            return
        }
        else{
            count=0
        }
        val name=pname.text.toString().trim()
        if(name.isEmpty()){
            pname.error="Enter the player name"
            Toast.makeText(applicationContext,"Player name Empty",Toast.LENGTH_SHORT).show()
            return
        }
        val pos=ppos.text.toString().trim()
        if(pos.isEmpty()){
            ppos.error="Enter the player position"
            Toast.makeText(applicationContext,"Player Position Empty",Toast.LENGTH_SHORT).show()
            return
        }
        val age=page.text.toString().trim()
        if(age.isEmpty()){
            page.error="Enter the player age"
            Toast.makeText(applicationContext,"Player Age Empty",Toast.LENGTH_SHORT).show()
            return
        }

        val battingpoints=batpts.text.toString().trim()
        if(battingpoints.isEmpty()){
            batpts.error="Enter the player batting points"
            Toast.makeText(applicationContext,"Batting Points Empty",Toast.LENGTH_SHORT).show()
            return
        }
        val bowlingpts=bowlpts.text.toString().trim()
        if(bowlingpts.isEmpty()){
            bowlpts.error="Enter the player bowling points"
            Toast.makeText(applicationContext,"Bowling Points Empty",Toast.LENGTH_SHORT).show()
            return
        }
        val allpoints=allpts.text.toString().trim()
        if(allpoints.isEmpty()){
            allpts.error="Enter the player all rounder position"
            Toast.makeText(applicationContext,"All Rounder Points Empty",Toast.LENGTH_SHORT).show()
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Players")
        val playerId=ref.push().key
        val player = Player(playerId!!,name,pos,age.toLong(),battingpoints.toLong(),bowlingpts.toLong(),allpoints.toLong(),finalurl.toString())
        if (playerId != null) {
            ref.child(playerId).setValue(player).addOnCompleteListener{
                Toast.makeText(applicationContext,"Player saved!",Toast.LENGTH_SHORT).show()
            }
        }


    }
    fun colourchangeDark(b: Button) {
        b.setBackgroundDrawable(resources.getDrawable(R.drawable.roundedbuttondark))
    }
    fun colourchangeLight(b: Button) {
        b.setBackgroundDrawable(resources.getDrawable(R.drawable.roundedbutton))
    }
}
