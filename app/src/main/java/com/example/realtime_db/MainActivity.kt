package com.example.realtime_db

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtime_db.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myRef = database.getReference()
        var count = 0
        val name = binding.addName.text.toString()
        val id = binding.addId.text.toString()
        val age = binding.addAge.text.toString()

binding.btnAddUser.setOnClickListener {
    val person = hashMapOf(
        "name" to name ,
        "id" to id ,
        "age" to age
    )
    myRef.child("person").child("$count").setValue(person)
    count++
    Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
}

binding.getAddUser.setOnClickListener {

    myRef.addValueEventListener(object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            val value = snapshot.getValue()
            binding.viewData.text = value.toString()
            Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()

        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()

        }

    })

}

    }
}
