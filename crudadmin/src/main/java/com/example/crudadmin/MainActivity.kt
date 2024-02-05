package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudadmin.databinding.ActivityMainBinding
import com.example.crudadmin.databinding.ActivityUploadBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uploadbtn1.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.updatebtn1.setOnClickListener {
            val intent = Intent(this, UpdateInfo::class.java)
            startActivity(intent)
            finish()

        }
        binding.deletebtn1.setOnClickListener {
            val intent = Intent(this, DeleteInfo::class.java)
            startActivity(intent)
            finish()
        }
    }
}