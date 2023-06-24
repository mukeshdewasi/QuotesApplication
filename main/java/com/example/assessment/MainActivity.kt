package com.example.assessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.assessment.Adapter.AppAdapter
import com.example.assessment.Data.AppData
import com.example.assessment.Database.AppDatabase
import com.example.assessment.Preference.PrefManager
import com.example.assessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    var list = mutableListOf<AppData>()
    lateinit var aAdapter:AppAdapter
    lateinit var db:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        db = Room.databaseBuilder(this, AppDatabase::class.java, "app.db").allowMainThreadQueries()
            .build()


      /*  list.add(AppData("Alone"))
        list.add(AppData("Angry"))
        list.add(AppData("Anniversary"))
        list.add(AppData("Attitude"))
        list.add(AppData("Awesome"))
        list.add(AppData("Awkward Moment"))
        list.add(AppData("Beard"))
        list.add(AppData("Beautiful"))
        list.add(AppData("Best"))
        list.add(AppData("Bike"))*/

            aAdapter= AppAdapter(this,list)
            binding.recyclerView.layoutManager=GridLayoutManager(this,2)
            binding.recyclerView.adapter=aAdapter

           // updatelist()

    }

    private fun updatelist() {
        list=db.userDao().getAllRecords()
        aAdapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()

        if (db!=null){
            updatelist()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
        R.id.profile->{
            true
        }
            R.id.logout->{
                    var manager=PrefManager(this)
                    manager.updateloginStatus(false)
                true
            }
            R.id.AddCategory ->{
                    startActivity(Intent(this,CategoryAddActivity::class.java))
                true
            }
            R.id.AddQuotes ->{
                startActivity(Intent(this,QuotesAddActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }
}