package com.example.pruebateatro

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

enum class ProviderType{
    BASIC
}

class PaginaPrincipal : AppCompatActivity() {
    class SharedApp : Application() {
        companion object {

            lateinit var diasdelmes: MutableList<FECHA>
        }

        override fun onCreate() {
            super.onCreate()
//guardamos una lista de la clase fecha, que nos debe devolver la peticion
            diasdelmes  = ArrayList()
        }
    }

    data class EMPRESA(

        @SerializedName("CEDULAJURIDICA") var CEDULAJURIDICA: String,
        @SerializedName("DESPEDIDA") var DESPEDIDA: String,
        @SerializedName("DIRECCION") var DIRECCION: String,
        @SerializedName("DIRECCIONXY") var DIRECCIONXY: String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("IMAGENURL") var IMAGENURL: String,
        @SerializedName("IMAGENURLMOVIL") var IMAGENURLMOVIL: String,
        @SerializedName("IMPUESTOAPLICA") var IMPUESTOAPLICA: String,
        @SerializedName("NOMBRE") var NOMBRE: String,
        @SerializedName("PAGADO") var PAGADO: String,
        @SerializedName("SALUDO") var SALUDO: String,
        @SerializedName("TELEFONO1") var TELEFONO1: String,
        @SerializedName("TELEFONO2") var TELEFONO2: String,
        @SerializedName("URLESCENARIO") var URLESCENARIO: String

    )

    annotation class SerializedName(val value: String)

    data class EVENTO(

        @SerializedName("DESCRIPCION") var DESCRIPCION: String,
        @SerializedName("DETALLE") var DETALLE: String,
        @SerializedName("EMPRESA") var EMPRESA: String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("IMAGENURL") var IMAGENURL: String,
        @SerializedName("IMPUESTO") var IMPUESTO: Number,
        @SerializedName("NOMBRE") var NOMBRE: String


    )
    data class LISTA_PRECIOS(

        @SerializedName("CODIGO_ACCESO") var CODIGO_ACCESO:String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("NOMBRE") var NOMBRE:String,
        @SerializedName("NUMERO") var NUMERO:Number,
        @SerializedName("TOTAL") var TOTAL:Number

    )

    //clase padre donde se utiliza relaciones de composicion con las clases anteriores para obtener una unica clase con los
    //atributos necesarios que devuelve la peticion
    data class FECHA(

        @SerializedName("ANNO") var ANNO: Number,
        @SerializedName("CERRADO") var CERRADO: String,
        @SerializedName("DIA") var DIA: Number,
        @SerializedName("EMPRESA") var EMPRESA: EMPRESA,
        @SerializedName("EVENTO") var EVENTO: EVENTO,
        @SerializedName("HORAS_CADUCA") var HORAS_CADUCA: Number,
        @SerializedName("HORA_FIN") var HORA_FIN:String,
        @SerializedName("HORA_INICIA") var HORA_INICIA:String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("LISTA_PRECIOS") var LISTA_PRECIOS: LISTA_PRECIOS,
        @SerializedName("MES") var MES: Number,
        @SerializedName("NUMERO_HORA_FIN") var NUMERO_HORA_FIN: Number,
        @SerializedName("NUMERO_HORA_INICIA") var NUMERO_HORA_INICIA: Number,
        @SerializedName("NUMERO_MINUTO_FIN") var NUMERO_MINUTO_FIN: Number,
        @SerializedName("NUMERO_MINUTO_INICIA") var NUMERO_MINUTO_INICIA: Number,
        @SerializedName("TERMINAL") var TERMINAL:String

    )


    lateinit var mRecyclerView : RecyclerView
    val mAdapter : adapter = adapter()
    //funcion de carga del recycler view con la lista de fechas que se debe llamar al realizar la peticion
    fun setUpRecyclerView(){


        mRecyclerView = findViewById(R.id.listadeobras) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(SharedApp.diasdelmes, this)
        mRecyclerView.adapter = mAdapter

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler)

        btn_obras.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(APIService::class.java).registrationPost("listarparamovil","11","2020").execute()
                // Log.d("",call.body().toString())
                //  val puppies = call.body() as String?
                runOnUiThread {
                    SharedApp.diasdelmes = call.body()!!
                    Log.i("", SharedApp.diasdelmes.toString())
                    //Log.d("",puppies.toString())
                    setUpRecyclerView()
                    // Log.d("",call.body().toString())
                }
            }
        }
    }

    //data class DogsResponse (@SerializedName("status") var status:String, @SerializedName("message") var images: List<String>)

    interface APIService {
        @POST("Calendario")
        @FormUrlEncoded
        fun registrationPost(
            @Field("op") op: String,
            @Field("mes") mes: String,
            @Field("anno") anno: String


        ): Call<MutableList<FECHA>>
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://taquillas.sistemcr.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}