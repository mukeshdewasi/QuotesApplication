package com.example.assessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assessment.Preference.PrefManager
import com.example.assessment.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.btnSignup.setOnClickListener {
                    var intent=Intent(this,SignUpAcivity::class.java)
                    startActivity(intent)
            }

            binding.btnLogin.setOnClickListener {
                    var email=binding.etEmail.text.toString()
                    var password=binding.etPassword.text.toString().trim()

                    login(email,password)
            }

    }

    private fun login(email: String, password: String) {
        var manager= PrefManager(this)
        var memail=manager.getEmail()
        var mpassword=manager.getPassword()
        var mname=manager.getFname()

        if (email==memail&&password==mpassword){
            manager.updateloginStatus(true)

            Toast.makeText(this, "Welcome,$mname", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }else{
            Toast.makeText(this, "Invalid Credential    ", Toast.LENGTH_SHORT).show()
        }
    }
}