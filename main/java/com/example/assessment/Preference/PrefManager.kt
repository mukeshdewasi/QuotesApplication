package com.example.assessment.Preference

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable.Creator
import android.view.View
import androidx.lifecycle.viewmodel.savedstate.R
import javax.net.ssl.SSLEngineResult.Status

class PrefManager(context:Context) {

    val PREF_NAME="mukesh"

    val KEY_FNAME="fname"
    val KEY_LNAME="lname"
    val KEY_PASSWORD="password"
    val KEY_CPASSWORD="cpassword"
    val KEY_EMAIL="email"
    val KEY_CONTACT="contact"
    val KEY_DOB="dob"
    val KEY_GENDER="gender"
    val KEY_ISLOGIN="islogin"

    private var preference:SharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    private var editor:SharedPreferences.Editor=preference.edit()

        fun Create(fname:String,lname:String,password:String,cpassword:String,email:String,contact:String,dob:String,gender:String){
                editor.putString(KEY_FNAME,fname)
                editor.putString(KEY_LNAME,lname)
                editor.putString(KEY_PASSWORD,password)
                editor.putString(KEY_CPASSWORD,cpassword)
                editor.putString(KEY_EMAIL,email)
                editor.putString(KEY_CONTACT,contact)
                editor.putString(KEY_DOB,dob)
                editor.putString(KEY_GENDER,gender)
                editor.commit()

        }
            fun getFname():String?{
                    return preference.getString(KEY_FNAME,"")
            }
             fun getLname():String? {
                 return preference.getString(KEY_LNAME, "")
             }
             fun getPassword():String?{
                return preference.getString(KEY_PASSWORD,"")
              }
             fun getCpassword():String?{
                 return preference.getString(KEY_CPASSWORD,"")
              }
             fun getEmail():String?{
                 return preference.getString(KEY_EMAIL,"")
             }
             fun getContact():String?{
                    return preference.getString(KEY_CONTACT,"")
             }
            fun getDob():String?{
                return preference.getString(KEY_DOB,"")
             }

            fun getLoginstuts():Boolean{
                return preference.getBoolean(KEY_ISLOGIN,false)
               }
            fun updateloginStatus(status:Boolean){
                    editor.putBoolean(KEY_ISLOGIN,status)
                    editor.commit()
            }
            fun clearData(){
                    editor.commit()
                    editor.clear()
            }




}