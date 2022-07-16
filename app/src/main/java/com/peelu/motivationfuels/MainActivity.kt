package com.peelu.motivationfuels

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.peelu.motivationfuels.adapter.CategoryAdapter
import com.peelu.motivationfuels.databinding.ActivityMainBinding
import com.peelu.motivationfuels.model.CategoryModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        db.collection("Quotes").addSnapshotListener{ value,_ ->
            val quotes= arrayListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel::class.java)
            quotes.addAll(data!!)
            binding.rcvCategory.layoutManager = LinearLayoutManager(this)
            binding.rcvCategory.adapter = CategoryAdapter(this,quotes)
        }
//        val shayari = arrayListOf("Love Shayari","Attitude Shayari","Romantic Shayari")



        binding.btnMenu.setOnClickListener{
            if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        binding.navigationLayout.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.share -> {
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "Install this App to get new hindi Shayariaan"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    true
                }
                R.id.rate -> {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.peelu.merishayari")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.peelu.merishayari")))
                    }
                    true
                }
                R.id.more -> {
                    val uri = Uri.parse("market://details?id=com.wordel")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
                    }
                    true
                }else -> {
                false
            }
            }
        }

    }
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}