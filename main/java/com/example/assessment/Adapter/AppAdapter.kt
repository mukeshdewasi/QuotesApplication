package com.example.assessment.Adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.assessment.Data.AppData
import com.example.assessment.Database.AppDatabase
import com.example.assessment.QuotesActivity
import com.example.assessment.R
import com.example.assessment.databinding.IconBinding
import java.io.File

class AppAdapter(var context: Context, var list: MutableList<AppData>) :
    RecyclerView.Adapter<AppAdapter.MyViewHolder>() {
    lateinit var binding: IconBinding
    lateinit var db: AppDatabase

    class MyViewHolder(var bind: IconBinding) : RecyclerView.ViewHolder(bind.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = IconBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        var user = list[position]

        holder.bind.tvTittal.text = "${user.tittle}"

        var file = File(context.filesDir, "images/${user.image}")

        /*Glide
            .with(context)
            .load(file)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.bind.ivImage)*/
        var bitmap=BitmapFactory.decodeFile(file.path)
        holder.bind.ivImage.setImageBitmap(bitmap)


        holder.bind.cardView.setOnClickListener {
            var intent = Intent(context, QuotesActivity::class.java)
            intent.putExtra("LIST", user)

            context.startActivity(intent)

        }

        holder.bind.cardView.setOnLongClickListener {
            var builder = AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete from this Data?")
                .setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                    db = Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
                        .allowMainThreadQueries()
                        .build()
                    var userDAO = db.userDao()
                    userDAO.deleteRecords(user)
                    notifyItemRemoved(position)
                    list.removeAt(position)

                    Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show()


                }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                })
            builder.show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(list: MutableList<AppData>) {
        this.list = list
        notifyDataSetChanged()
    }
}


