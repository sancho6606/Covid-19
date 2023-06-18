package com.sancho.covid_19.network

import com.sancho.covid_19.model.CountriesItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("countries")
    fun getAllCountries(): Call<ArrayList<CountriesItem>>

}