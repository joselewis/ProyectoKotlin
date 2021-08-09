package com.example.pruebateatro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.calendario_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class calendario : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: adapter = adapter()
    var diaEvento: MutableList<CapaDatos.FECHA> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendario_layout)

        calendarObras.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mes = month + 1
            obtenerObras(mes.toString(), year.toString(), dayOfMonth)
        }
    }

    fun setUpRecyclerView() {

        mRecyclerView = findViewById(R.id.recylerViewObrasActivas) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(diaEvento, this)
        mRecyclerView.adapter = mAdapter
    }

    fun obtenerObras(mes: String, anno: String, dia: Int) {

        diaEvento.clear()
        //textView8.setText("Eventos: ")

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(calendario.APIService::class.java)
                .registrationPost("listarparamovil", mes, anno).execute()

            runOnUiThread {
                CapaDatos.SharedApp.diasdelmes = call.body()!!

                for (i in CapaDatos.SharedApp.diasdelmes) {

                    if (i.DIA.toInt() == dia) {

                        diaEvento.add(i)
                        break
                    }
                }
                setUpRecyclerView()
            }
        }
    }

    interface APIService {
        @POST("Calendario")
        @FormUrlEncoded
        fun registrationPost(
            @Field("op") op: String,
            @Field("mes") mes: String,
            @Field("anno") anno: String

        ): Call<MutableList<CapaDatos.FECHA>>
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://taquillas.sistemcr.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}