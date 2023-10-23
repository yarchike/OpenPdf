package com.example.myapplication

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var reference: Long = 0
    var name = ""
    lateinit var downloadManager:Downloader
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            registerReceiver(onDownLoadComplete,  IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        val file = "https://hands.ru/media/agent_report/6f/18/6f18116f4c40fecf24325ae70b950f35.pdf"
            downloadManager =  AndroidDownloader(contex = this)

      findViewById<Button>(R.id.download).setOnClickListener {
          name = "${Random.nextInt()}.pdf"
          downloadManager.downloadFile(file)




       }
    }

    val onDownLoadComplete = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if(reference == id){
                Log.d("MyLogS", "endLoad")
                val file  = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$name"
                val files = File(file)
                Log.d("MyLogS", "files ${files}")
                findViewById<PDFView>(R.id.pdfView)
                    .fromFile(files)
//                    .fromUri(File(file))
                    .load()
            }
        }

    }



}