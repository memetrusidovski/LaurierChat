package com.example.cp470chatbot

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cp470chatbot.databinding.ActivityWelcomPageBinding
import com.google.android.material.transition.platform.MaterialSharedAxis

class Welcom_page : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_welcom_page);
        var bgVideoView: VideoView = findViewById(R.id.videoview)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.chatbotmovief)
        bgVideoView.setVideoURI(uri)
        bgVideoView.start()


        bgVideoView.setOnPreparedListener { it.isLooping = true }

        val buttonClick = findViewById<Button>(R.id.loginbutton)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

//    override fun onPostResume() {
//        gVideoView?.resume()
//        super.onPostResume()
//    }


    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_welcom_page)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}
