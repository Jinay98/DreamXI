package com.example.android.dreamxi

import android.net.Uri

class Player(val id:String,val name:String,val pos:String,val age:Long,val batpts:Long,val bowlpts:Long,val allpts:Long,val url:String){
    constructor() : this("12345","Raj","Opener",20,75,75,75," "){

    }
}
