package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityDeleteInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteInfo : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteInfoBinding

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteDataBtn1.setOnClickListener {
            val vehicleNumber = binding.dellVehicleNum1.text.toString()
            if(vehicleNumber.isNotEmpty()){
                deleteData(vehicleNumber)

            }else Toast.makeText(this,"Please enter vehicle number", Toast.LENGTH_LONG).show()
        }


    }

    private fun deleteData(vehicleNumber: String){

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        //remove value is remove the data from database
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.dellVehicleNum1.text.clear()
            Toast.makeText(this,"Deleted", Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(this,"Unable to delete", Toast.LENGTH_LONG).show()


        }


    }
}