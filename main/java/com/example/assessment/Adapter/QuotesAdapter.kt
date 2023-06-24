package com.example.assessment.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.Data.QuotesData
import com.example.assessment.QuotesActivity
import com.example.assessment.databinding.IconBinding
import com.example.assessment.databinding.QuotesBinding

class QuotesAdapter(var context: Context, var list: MutableList<QuotesData>) :
    RecyclerView.Adapter<QuotesAdapter.MyViewHolder>() {

        lateinit var binding :QuotesBinding

    class MyViewHolder(var bind :QuotesBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.MyViewHolder {
        binding=QuotesBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      var list=list[position]
        holder.bind.layout1.setCardBackgroundColor(context.resources.getColor(list.color))
        holder.bind.tvQuotes.text="${list.Quotes}"





    }

    override fun getItemCount(): Int {
        return list.size
    }


}