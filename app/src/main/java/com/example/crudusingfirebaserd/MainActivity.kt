package com.example.crudusingfirebaserd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudusingfirebaserd.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchbtn1.setOnClickListener {
            val searchVehicleNumber  = binding.searchVehicleNumber1.text.toString()
            if(searchVehicleNumber.isNotEmpty()){
                readData(searchVehicleNumber)
            }else{
                Toast.makeText(this,"Please enter vehicle number",Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        //to retrieve data, we use get method
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            //it means vehicle number

            if(it.exists()){
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRto = it.child("vehicleRTO").value

                Toast.makeText(this,"Result Found!",Toast.LENGTH_SHORT).show()

                binding.searchVehicleNumber1.text.clear()
                binding.getOwnerName1.text = ownerName.toString()
                binding.getVehicleBrand1.text = vehicleBrand.toString()
                binding.getVehicleRto1.text = vehicleRto.toString()

            }else{
                Toast.makeText(this,"Vehicle Number doesn't exist!",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong!",Toast.LENGTH_SHORT).show()

        }

    }
}