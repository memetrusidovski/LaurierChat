package com.example.cp470chatbot

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.cp470chatbot.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);
        var username: TextView = findViewById(R.id.Email_text)
        var password: TextView = findViewById(R.id.password_text)
        var loginbutton: Button = findViewById(R.id.sign_in_button)
        loginbutton.setOnClickListener {
            startActivity(intent)

            if ((username.text.toString().trim() != "")
                && (isValidEmailStudent(username.text.toString().trim()))
                && (isValidPassStudent(password.text.toString().trim()))) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            else if((username.text.toString().trim() != "")
                && (isValidEmailAdmin(username.text.toString().trim()))
                        && (isValidPassStudent(password.text.toString().trim()))){
                val intent = Intent(this, AdminDashboard::class.java)
                startActivity(intent)

            }

            else{
                Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private fun isValidEmailStudent(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches() &&
                target=="nadeem_ahmad@hotmail.ca"
    }
    private fun isValidPassStudent(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target=="Password"
    }

    private fun isValidEmailAdmin(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches() &&
                target=="admin_123@hotmail.ca"
    }
    private fun isValidPassAdmin(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target=="Password"
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_login)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}