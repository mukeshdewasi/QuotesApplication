package com.example.assessment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.room.Room
import com.example.assessment.Adapter.AppAdapter
import com.example.assessment.DAO.UserDao
import com.example.assessment.Database.AppDatabase
import com.example.assessment.databinding.ActivityQuotesAddBinding

class QuotesAddActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuotesAddBinding

    lateinit var db : AppDatabase
    var userDao = db.userDao()

    val data=userDao.getAllRecords()
    val items=data.map { it .tittle}.toTypedArray()

   /* var quotes= arrayOf(
        "SelectColor",
        "Blue",
        "Red",
        "Yellow",
        "Pink"
    )


    var color=""
    lateinit var quotesaddpter: ArrayAdapter<String>*/

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

      db= Room.databaseBuilder(this, AppDatabase::class.java, "app.db").allowMainThreadQueries()
            .build()

         val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
         binding.quotesSpinner.adapter=adapter

    /*     val selectedItem = data.firstOrNull { it.isSelected }?.tittle
         val selectedIndex= items.indexOf(selectedItem)
         binding.quotesSpinner.setSelection(selectedIndex)*/




        //   val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, quotes)


     /*   quotesaddpter = object :
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, quotes) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    return super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                }
                return view
            }
        }
        binding.quotesSpinner.adapter = quotesaddpter

        binding.quotesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }*/

    }

}