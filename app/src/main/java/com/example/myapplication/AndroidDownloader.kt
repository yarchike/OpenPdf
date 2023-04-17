package com.example.myapplication

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(  private val contex: Context): Downloader {


    private val downloadManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        contex.getSystemService(DownloadManager::class.java)
    } else {
        contex.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }


    override fun downloadFile(url: String) : Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
            .setTitle("image.jpeg")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
        return downloadManager.enqueue(request)


    }
}