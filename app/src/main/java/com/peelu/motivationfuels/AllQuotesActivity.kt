package com.peelu.motivationfuels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.peelu.motivationfuels.adapter.AllQuotesAdapter
import com.peelu.motivationfuels.databinding.ActivityAllQuotesBinding
import com.peelu.motivationfuels.model.AllQuotesModel


class AllQuotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAllQuotesBinding
    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // instance of firebase database
        db = FirebaseFirestore.getInstance()

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        binding.catName.text = name.toString()

        db.collection("Quotes").document(id!!).collection("all")
            .addSnapshotListener{ value,_ ->
                val quotesList = arrayListOf<AllQuotesModel>()
                val data = value?.toObjects(AllQuotesModel::class.java)
                quotesList.addAll(data!!)
                binding.rcvAllQuotes.layoutManager = LinearLayoutManager(this)
                binding.rcvAllQuotes.adapter = AllQuotesAdapter(this,quotesList)

            }

    }
}