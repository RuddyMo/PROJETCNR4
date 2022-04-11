package com.example.projetcnr4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import com.example.projetcnr4.network.DonneesService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var tvResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResponse = findViewById(R.id.tvResponse)

        //TODO: creation retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.17.50.117/Slim/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val donneesService = retrofit.create(DonneesService::class.java)

        // TODO2: call api
        val result = donneesService.getDonnees()

        result.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    tvResponse.text = response.body();
                    /*val accelAngX_Mat = result?.get("accelAngX_Mat")?.

                    tvResponse.text = "$accelAngX_Mat "*/
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(applicationContext, "Erreur serveur", Toast.LENGTH_SHORT).show()
            }

        })


    }
}


/*  override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       val donneesService = RetrofitInstance.buildDonneeService()
       donneesService.getDonnees().enqueue(object : retrofit2.Callback<List<Donnee>>{
           override fun onResponse(call: Call<List<Donnee>>, response: Response<List<Donnee>>) {
               if(response.isSuccessful){
                   val data = response.body()
                   data?.let{
                       donneesAdapter = DonneesAdapter(data)

                   }
               }
           }

           override fun onFailure(call: Call<List<Donnee>>, t: Throwable) {
               Toast.makeText(this@MainActivity, "Erreur serveur", Toast.LENGTH_LONG).show()
           }

       })
   }*/