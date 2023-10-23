package com.example.myapplication

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import kotlin.random.Random

class AndroidDownloader(  private val contex: Context): Downloader {


    private val downloadManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        contex.getSystemService(DownloadManager::class.java)
    } else {
        contex.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }


    override fun downloadFile(url: String) : Long {
        Log.d("MyLogS", "downloadFile")
        val name = "${Random.nextInt()}.pdf"
        val request = DownloadManager.Request(url.toUri())
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
            .setTitle(name)
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        return downloadManager.enqueue(request)


    }

}