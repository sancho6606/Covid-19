package com.sancho.covid_19

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sancho.covid_19.databinding.RecyclerviewItemBinding
import com.sancho.covid_19.model.CountriesItem

class CoronaAdapter constructor(

    val context: Context,
    var arrayList: ArrayList<CountriesItem>,

):RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder>() {

    class CoronaViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoronaViewHolder {
        val view=RecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CoronaViewHolder(view)
    }
    override fun onBindViewHolder(holder: CoronaViewHolder, position: Int) {
        holder.binding.apply {
            textviewcountryname.text=arrayList.get(position).country
            textviewcountrycases.text="Cases : ${arrayList.get(position).cases}"
            textviewcountryrec.text="Recovered : ${arrayList.get(position).recovered}"
            textviewcountrydeaths.text="Deaths : ${arrayList.get(position).deaths}"
            Glide.with(imageviewcountryflag).load(arrayList.get(position).countryInfo.flag).placeholder(R.drawable.covid).into(imageviewcountryflag)
            relativelay.setOnClickListener {
                val intent= Intent(context,MainActivity2::class.java)
                intent.putExtra("flag",arrayList.get(position).countryInfo.flag)
                intent.putExtra("country",arrayList.get(position).country)
                intent.putExtra("cases",holder.binding.textviewcountrycases.text.toString())
                intent.putExtra("rec",holder.binding.textviewcountryrec.text.toString())
                intent.putExtra("death",holder.binding.textviewcountrydeaths.text.toString())
                context.startActivity(intent)
            }

        }
    }
    override fun getItemCount(): Int =arrayList.size

    fun filterList(filteredList:ArrayList<CountriesItem>){
        arrayList = filteredList
        notifyDataSetChanged()
    }



}