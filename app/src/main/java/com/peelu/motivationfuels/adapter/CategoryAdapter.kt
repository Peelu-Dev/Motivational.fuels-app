package com.peelu.motivationfuels.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peelu.motivationfuels.AllQuotesActivity
import com.peelu.motivationfuels.MainActivity
import com.peelu.motivationfuels.databinding.ItemCategoryBinding
import com.peelu.motivationfuels.model.CategoryModel

class CategoryAdapter(private val mainActivity: MainActivity, private val quotes: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CatViewHolder>(){
    class CatViewHolder( val binding:ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    private val colorsList = arrayListOf("#55efc4","#74b9ff","#d63031","#e17055","#9b59b6","#e74c3c","#f1c40f","#c0392b")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if(position%8==0){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[0]))
        }else if(position%8==1){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[1]))
        }else if(position%8==2){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[2]))
        }else if(position%8==3){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[3]))
        }else if(position%8==4){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[4]))
        }else if(position%8==5){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[5]))
        }else if(position%8==6){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[6]))
        }else if(position%8==7){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[7]))
        }

        holder.binding.itemText.text = quotes[position].name.toString()
        holder.binding.root.setOnClickListener{
            val intent = Intent(mainActivity, AllQuotesActivity::class.java)
            intent.putExtra("id",quotes[position].id)
            intent.putExtra("name",quotes[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = quotes.size
}