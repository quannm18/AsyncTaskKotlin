package com.quannm18.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            XuLyCongViec().execute()
        }
    }
    inner class XuLyCongViec : AsyncTask<Void, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            tvContent.text = "Starting..."
        }
        override fun doInBackground(vararg p0: Void?): String {
            for(i in 0..6){
                publishProgress("Xong $i")
            }
            return "Finished"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            tvContent.append(result)
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            tvContent.append(values[0])

        }
    }
}