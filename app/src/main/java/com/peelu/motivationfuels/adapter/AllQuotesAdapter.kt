package com.peelu.motivationfuels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peelu.motivationfuels.AllQuotesActivity
import com.peelu.motivationfuels.R
import com.peelu.motivationfuels.databinding.ItemQuotesBinding
import com.peelu.motivationfuels.model.AllQuotesModel

class AllQuotesAdapter(
    private val allQuotesActivity: AllQuotesActivity,
    private val quotesList: ArrayList<AllQuotesModel>
) : RecyclerView.Adapter<AllQuotesAdapter.QuotesViewHolder>(){
    // implementing all members
    // added shayariViewHolder class
    class QuotesViewHolder(val binding: ItemQuotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        // binding ShayariViewHolder with ItemShayariBinding
        return  QuotesViewHolder(ItemQuotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {


        if(position%8==0){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_1)
        }else if(position%8==1){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_2)
        }else if(position%8==2){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_3)
        }else if(position%8==3){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_4)
        }else if(position%8==4){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_5)
        }else if(position%8==5){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_6)
        }else if(position%8==6){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_7)
        }else if(position%8==7){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_8)
        }

        holder.binding.itemQuotes.text = quotesList[position].data.toString()
    }

    override fun getItemCount() = quotesList.size
}