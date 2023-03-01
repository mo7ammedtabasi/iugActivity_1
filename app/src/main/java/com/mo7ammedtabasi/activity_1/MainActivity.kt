package com.mo7ammedtabasi.activity_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mo7ammedtabasi.activity_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val pName = binding.pName.text.toString()
            val pId = binding.pId.text.toString()
            val pAge = binding.pAge.text.toString()

            if (pName.isNotEmpty() && pId.isNotEmpty() && pAge.isNotEmpty()){

                val person = hashMapOf(
                    "pName" to pName,
                    "pId" to pId,
                    "pAge" to pAge
                )

                db.collection("Person")
                    .add(person)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this@MainActivity, "Save", Toast.LENGTH_SHORT).show()
                        Log.d("firestormTAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                        binding.pName.text?.clear()
                        binding.pId.text?.clear()
                        binding.pAge.text?.clear()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                        Log.w("firestormTAG", "Error adding document", e)
                    }
            } else {
                Toast.makeText(this@MainActivity, "some failed is empty", Toast.LENGTH_SHORT).show()

            }

        }


    }
}