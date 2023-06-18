package com.sancho.covid_19

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sancho.covid_19.databinding.ActivityMain2Binding
import com.sancho.covid_19.model.CountriesItem

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var countriesItem: CountriesItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            var flag=intent.getStringExtra("flag")
            var name=intent.getStringExtra("country")
            var cases=intent.getStringExtra("cases")
            var recovered=intent.getStringExtra("rec")
            var deaths=intent.getStringExtra("death")
            textviewname.text=name
            textviewcases.text=cases
            textviewrecovered.text=recovered
            textviewdeath.text=deaths
            Glide.with(this@MainActivity2).load(flag).placeholder(R.drawable.covid).into(imageviewflag)
        }
    }
}