package com.example.pr29

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    lateinit var btnOpenCamera:Button
    lateinit var btnOpenBrowser:Button
    lateinit var btnOpenPhone:Button
    lateinit var btnOpenMaps:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenCamera = findViewById(R.id.btnOpenCamera)
        btnOpenCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        btnOpenBrowser = findViewById(R.id.btnOpenBrowser)
        btnOpenBrowser.setOnClickListener {
            openBrowser()
        }

        btnOpenPhone = findViewById(R.id.btnOpenPhone)
        btnOpenPhone.setOnClickListener {
            openPhone()
        }

        btnOpenMaps = findViewById(R.id.btnOpenMaps)
        btnOpenMaps.setOnClickListener {
            openMaps()
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun openPhone() {
        val phoneNumber = "tel:+)+79139022303" // Замените на нужный номер телефона
        val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
        startActivity(phoneIntent)
    }

    private fun openMaps() {
        val latitude = 55.026899
        val longitude = 82.911605
        val label = "Дзержинского, 26, Новосибирск, Новосибирская обл., 630015"

        val geoUri = "geo:$latitude,$longitude?q=$latitude,$longitude($label)"
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        mapIntent.setPackage("com.google.android.apps.maps") // Убедитесь, что у пользователя установлено приложение Google Maps
        startActivity(mapIntent)
    }


    private fun openBrowser() {
        val url = "https://docs.google.com/spreadsheets/d/1I620rAZNanOrKJ1YFt016EAF8a-6TPaKzdJdRt1LlmI/edit#gid=1421707175"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            // Здесь можно обработать полученное изображение
        }
    }
}
