package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityMainBinding
import com.example.crudadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding

    //as refer the database
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton1.setOnClickListener {
            val ownerName1 = binding.ownerName1.text.toString()
            val vehicleBrand1 = binding.vehicleBrand1.text.toString()
            val vehicleRto1 = binding.vehicleRto1.text.toString()
            val vehicleNumber1 = binding.vehicleNumber1.text.toString()

            //below is initialisation
            //inside getReference , we write database name as/or path name
            databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
            //Data Class and object
            val vehicleData = VehicleData(ownerName1,vehicleBrand1,vehicleRto1,vehicleNumber1)

            //we'll create a path like there we'll be a child inside a child we store the data.
            //inside vehicle number it'll store other data like name,brand...etc.
            //here we use 2 methods 1]for success 2]for failure
            //for store , we use setValue() method
            databaseReference.child(vehicleNumber1).setValue(vehicleData).addOnSuccessListener {
                binding.ownerName1.text.clear()
                binding.vehicleBrand1.text.clear()
                binding.vehicleRto1.text.clear()
                binding.vehicleNumber1.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }

        }













    }
}