package com.quannm18.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_read_data_from_internet.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class ReadDataFromInternetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data_from_internet)
        button2.setOnClickListener {
            ReadContentUrl().execute("https://stackoverflow.com/questions/39462397/intents-in-kotlin")
        }
    }
    inner class ReadContentUrl : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg params: String?): String {
            var content = StringBuilder();
            var url : URL = URL(params[0]);
            var urlConnection : HttpURLConnection
                = url.openConnection() as HttpURLConnection
            var inputStream : InputStream = urlConnection.inputStream
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)

            var line : String = ""
            try {
                do {
                    line = bufferedReader.readLine()
                    if (line!=null){
                        content.append(line)
                    }
                }while (line.trim() != null)
                bufferedReader.close()
            }catch (e: Exception){
                e.printStackTrace()
            }
            return  content.toString()
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.setText(result)
        }
    }
}