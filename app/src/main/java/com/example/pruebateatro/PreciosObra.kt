package com.example.pruebateatro

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class PreciosObra : AppCompatActivity(){

    /*class SharedApp : Application() {
        companion object {

            lateinit var precios: MutableList<CapaDatos.LISTA_PRECIOS>
        }

        override fun onCreate() {
            super.onCreate()
            //guardamos una lista de la clase fecha, que nos debe devolver la peticion
            precios  = ArrayList()
        }
    }

    lateinit var mRecyclerView : RecyclerView
    val mAdapterPrecios : adapter = adapter()
    //funcion de carga del recycler view con la lista de fechas que se debe llamar al realizar la peticion
    fun setUpRecyclerView(){


        TextView = findViewById(R.id.) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapterPrecios.RecyclerAdapter(SharedApp.precios, this)
        mRecyclerView.adapter = mAdapterPrecios

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler)

        btn_obras.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(APIService::class.java).comprarTiquetes("listarparamovil","11","2020").execute()
                // Log.d("",call.body().toString())
                //  val puppies = call.body() as String?
                runOnUiThread {
                    SharedApp.precios = call.body()!!
                    Log.i("", SharedApp.precios.toString())
                    //Log.d("",puppies.toString())
                    setUpRecyclerView()
                    // Log.d("",call.body().toString())
                }
            }
        }
    }

    //data class DogsResponse (@SerializedName("status") var status:String, @SerializedName("message") var images: List<String>)

    interface APIService {

        @POST("LISTA_PRECIOS")
        @FormUrlEncoded
        fun comprarTiquetes(
            @Field("op") op: String,
            @Field("idempresa") idempresa: String,
            @Field("id") id: String,
            @Field("calendario") calendario: String

        ): Call<MutableList<CapaDatos.LISTA_PRECIOS>>
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://taquillas.sistemcr.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/
}