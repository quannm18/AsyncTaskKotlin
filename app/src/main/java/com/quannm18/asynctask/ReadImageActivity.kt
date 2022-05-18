package com.quannm18.asynctask

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_read_image.*
import java.io.InputStream
import java.net.URL

class ReadImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_image)
        LoadImage().execute("https://i.stack.imgur.com/vou9q.jpg?s=64&g=1")
    }
    inner class LoadImage : AsyncTask<String,Void, Bitmap>(){
        override fun doInBackground(vararg params: String?): Bitmap {
            val url : URL = URL(params[0])
            val inputStream : InputStream = url.openConnection().getInputStream()
            var bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }
}