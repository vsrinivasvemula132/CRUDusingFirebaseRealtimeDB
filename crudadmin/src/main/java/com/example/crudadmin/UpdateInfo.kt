package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUpdateInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateInfo : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateInfoBinding

    //we'll use it for refer the database
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateDataBtn1.setOnClickListener {
            val updateVNum = binding.updateVehicleNumber1.text.toString()
            val updateOName = binding.updateOwnerName1.text.toString()
            val updateBrand = binding.updateVehicleBrand1.text.toString()
            val updateRTo = binding.updateVehicleRto1.text.toString()

            updateData(updateVNum,updateOName,updateBrand,updateRTo)
        }

    }

    private fun updateData(vehicleNumber: String, ownerName: String,vehicleBrand: String, vehicleRTO:String){

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        //mapOf method basically returns specific data which we mention in the key value format or syntax
        val vehicleData1 = mapOf<String, String>("ownerName" to ownerName,
            "vehicleBrand" to vehicleBrand,"vehicleRTO" to vehicleRTO)

        databaseReference.child(vehicleNumber).updateChildren(vehicleData1).addOnSuccessListener {
            binding.updateVehicleNumber1.text.clear()
            binding.updateOwnerName1.text.clear()
            binding.updateVehicleBrand1.text.clear()
            binding.updateVehicleRto1.text.clear()

            Toast.makeText(this,"Updated", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Unable to update",Toast.LENGTH_LONG).show()
        }

    }

}