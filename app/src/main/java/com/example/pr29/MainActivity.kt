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
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
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
