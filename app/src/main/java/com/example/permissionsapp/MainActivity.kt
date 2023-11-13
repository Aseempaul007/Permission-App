package com.example.permissionsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 100)
        }

        button.setOnClickListener {
            val i = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            i.data = Uri.fromParts("package", packageName, null)
            startActivity(i)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(
                this@MainActivity,
                "Permission granted for READ_CONTACTS",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(
                this@MainActivity,
                "Permission not granted for READ_CONTACTS",
                Toast.LENGTH_SHORT
            ).show()
            button.visibility = View.VISIBLE
        }
    }
}