package com.example.assessment

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import com.example.assessment.Preference.PrefManager
import com.example.assessment.databinding.ActivitySignUpAcivityBinding
import java.util.*

class SignUpAcivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpAcivityBinding
    var gender=""
    var dob=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            var fname=binding.fname.text.toString().trim()
            var lname=binding.lname.text.toString().trim()
            var email=binding.etEmail.text.toString().trim()
            var password=binding.etPassword.text.toString().trim()
            var cpassword=binding.etConfirmpassword.text.toString().trim()
            var dob=binding.etDob.text.toString().trim()
            var contact=binding.contact.text.toString().trim()



            createAccount(fname,lname,email,password,cpassword,dob,contact,gender)
        }
        binding.etDob.setOnFocusChangeListener { view, status ->
            if (status){
                    showDatePicker()
            }else{
                    binding.etDob.clearFocus()
            }
        }


    }

    private fun showDatePicker() {
        var calender= Calendar.getInstance()
        var tempArray=dob.split("/",limit=3)
        var myear=if (dob.isEmpty()) {
            calender.get(Calendar.YEAR)
        }else{
            tempArray[2].toInt()
        }
        var mmonth=if (dob.isEmpty()){
            calender.get(Calendar.MONTH)
        }else{
            tempArray[1].toInt()-1
        }
        var mday=if(dob.isEmpty()){
            calender.get(Calendar.DAY_OF_MONTH)
        }else{
                tempArray[0].toInt()
        }
        var datePicker= DatePickerDialog(
                this,
                        DatePickerDialog.OnDateSetListener { Datepicker, year, month, day ->
                            dob="${String.format("%02d",day)}/${String.format("%02d",month+1)}/$year"
                            binding.etDob.setText(dob)

                        },
                        myear,
                        mmonth,
                        mday
        )
        datePicker.show()
    }

    private fun createAccount(fname: String, lname: String, email: String, password: String, cpassword: String, dob: String, contact: String,gender:String){

            var manager=PrefManager(this)
            manager.Create(fname,lname,email,password,cpassword,dob,contact,gender)
             Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
            onBackPressed()

    }

       fun onRadioButtonClicked(view: View){
                when(view.id){
                    R.id.male -> gender="Male"
                    R.id.female -> gender="female"
                }
        }

}