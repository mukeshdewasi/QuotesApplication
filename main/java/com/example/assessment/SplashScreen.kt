package com.example.assessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {

    val PREF_NAME="mukesh"

    val KEY_ISLOGIN="islogin"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


              Thread(Runnable {
                    kotlin.run {
                        Thread.sleep(3000)
                        var prefernces=getSharedPreferences(PREF_NAME, MODE_PRIVATE)
                        var status=prefernces.getBoolean(KEY_ISLOGIN,true)

                            if (!status){
                                startActivity(Intent(this, LoginActivity::class.java))
                            }else{
                                startActivity(Intent(this, MainActivity::class.java))
                            }

                    }
                }).start()


    }
}

