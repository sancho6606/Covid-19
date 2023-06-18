package com.sancho.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sancho.covid_19.databinding.ActivityMainBinding
import com.sancho.covid_19.model.CountriesItem
import com.sancho.covid_19.network.RetrofitInstance.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var coronaAdapter: CoronaAdapter
    lateinit var countryarrayList: ArrayList<CountriesItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllCountry()

        binding.edittextsearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

        })

    }

    fun getAllCountry(){
        val call:Call<ArrayList<CountriesItem>> = api.getAllCountries()
        call.enqueue(object :Callback<ArrayList<CountriesItem>>{
            override fun onResponse(
                call: Call<ArrayList<CountriesItem>>,
                response: Response<ArrayList<CountriesItem>>
            ) {
                if (response.isSuccessful){
                    Log.d("sancho666666666", "onResponse: ${response.code()}")
                    val arrayList=response.body()!!

                    binding.apply {
                        countryarrayList=arrayList
                        recyclerviewallcountry.layoutManager=LinearLayoutManager(this@MainActivity)
                        coronaAdapter=CoronaAdapter(this@MainActivity,arrayList)
                        recyclerviewallcountry.adapter=coronaAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<CountriesItem>>, t: Throwable) {

            }
        })
    }

    fun filter(text:String){
        val searcharraylist=ArrayList<CountriesItem>()

        for (item:CountriesItem in countryarrayList){
            if (item.country.toLowerCase().contains(text.toLowerCase())){
                searcharraylist.add(item)
            }
        }
        coronaAdapter.filterList(searcharraylist)
    }

}