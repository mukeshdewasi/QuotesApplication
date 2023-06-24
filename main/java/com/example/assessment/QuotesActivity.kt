package com.example.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessment.Adapter.QuotesAdapter
import com.example.assessment.Data.AppData
import com.example.assessment.Data.QuotesData
import com.example.assessment.databinding.ActivityQuotesBinding

class QuotesActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuotesBinding
    var Qutoes = mutableListOf<QuotesData>()
    lateinit var qAdapter: QuotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)



        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        var list = intent.getParcelableExtra<AppData>("LIST")
        if (list != null) {
            supportActionBar!!.title = "${list.tittle}"

        }


        /*   Qutoes.add(QuotesData(1,"“Life is full of misery, loneliness, and suffering, and it’s all over much too soon.”", R.color.sky))
           Qutoes.add(QuotesData(2, "“Life is full of misery, loneliness, and suffering, and it’s all over much too soon.”", R.color.pink))
           Qutoes.add(QuotesData(3, "“Life is full of misery, loneliness, and suffering, and it’s all over much too soon.”", R.color.purple))
           Qutoes.add(QuotesData(4,  "“Life is full of misery, loneliness, and suffering, and it’s all over much too soon.”", R.color.baby))
           Qutoes.add(QuotesData(5,  "“Life is full of misery, loneliness, and suffering, and it’s all over much too soon.”", R.color.black))
           Qutoes.add(QuotesData(6,  "“Life is full of misery, loneliness, and suffering, and it’s all over much too soon.”", R.color.pink))*/



        qAdapter = QuotesAdapter(this, Qutoes)

        binding.recycleView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView2.adapter = qAdapter


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}