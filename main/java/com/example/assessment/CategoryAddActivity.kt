package com.example.assessment


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.room.Room
import com.example.assessment.Data.AppData
import com.example.assessment.Database.AppDatabase
import com.example.assessment.databinding.ActivityCategoryAddBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class CategoryAddActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryAddBinding

    var app: AppData? = null
    lateinit var db: AppDatabase

    private var galleryPermissionContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                pickImagefromGallery()
            }else{
                Toast.makeText(this, "Allow Permission", Toast.LENGTH_SHORT).show()
            }
        }

    private fun pickImagefromGallery() {
        var intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

     val galleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it!= null) {
            imageUri=it
            binding.ivImageadd.setImageURI(imageUri)
        }
    }
    var imageUri:Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(this, AppDatabase::class.java, "app.db").allowMainThreadQueries()
            .build()

        app = intent.getParcelableExtra("LIST")

        imageUri=createImageUri()



        binding.ivImageadd.setOnClickListener {
                galleryResult.launch("image/*")
                intent.putExtra("IMAGE","list")
        }
        binding.btnFlotAdd.setOnClickListener {
            galleryResult.launch("image/*")
            intent.putExtra("IMAGE","list")
        }


        binding.btnCategory.setOnClickListener {
            var Category = binding.etCategory.text.toString().trim()

            if (imageUri !=null) {
                var bitmap = uriToBitmap(imageUri!!)
                bitmap?.let {
                    saveImageToInternalStorage(this, it,Category)
                }
            }

    //        insertRecord(Category)
            onBackPressedDispatcher.onBackPressed()
        }


    }

    private fun saveImageToInternalStorage(context:Context,bitmap: Bitmap,Category: String) {
        var filename="IMG_${System.currentTimeMillis()}.png"
        var directory=File(filesDir,"images")

        if (!directory.exists()){
                directory.mkdir()
        }
        try {
                var file=File(directory,filename)
                var fout=FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fout)
                fout.close()

                var obj=AppData(image = filename, tittle = Category)
            Toast.makeText(this, "file saved", Toast.LENGTH_SHORT).show()
            db.userDao().insertUser(obj)

        }catch ( e:Exception){
            e.printStackTrace()
        }

    }

    private fun uriToBitmap(imageUri: Uri): Bitmap?{
        var inputStream:InputStream?=null
        try {
            inputStream =contentResolver.openInputStream(imageUri)

            return BitmapFactory.decodeStream(inputStream)
        }catch (e:IOException){
            e.printStackTrace()
        }finally {
            inputStream!!.close()
        }
        return null

    }

    private fun createImageUri(): Uri? {
        var filename="${System.currentTimeMillis()}.png"
        var file=File(filesDir,filename)
        return FileProvider.getUriForFile(
            this,
            "com.example.assessment.fileprovider",
            file

        )

    }


    /*private fun insertRecord(Category: String) {

        var app = AppData(tittle = Category, )

        try {
            db.userDao().insertUser(app)
            Toast.makeText(this, "Category Added Successfuly", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==100 && resultCode== RESULT_OK){
                data?.let {
                        binding.ivImageadd.setImageURI(it.data)
                }
        }

    }
}



