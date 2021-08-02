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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

enum class ProviderType{
    BASIC
}

class PaginaPrincipal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var  databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    class SharedApp : Application() {
        companion object {

            lateinit var diasdelmes: MutableList<CapaDatos.FECHA>
        }

        override fun onCreate() {
            super.onCreate()
            diasdelmes  = ArrayList()
        }
    }

    lateinit var mRecyclerView : RecyclerView
    val mAdapter : adapter = adapter()

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

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("User")
        loadProfile()

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

    private fun loadProfile(){

        val user = auth.currentUser
        val userReference = databaseReference?.child(user?.uid!!)

        userReference?.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                textViewUsuario.text = "Bienvenido: " + snapshot.child("Name").value.toString() + " " +
                        snapshot.child("lastName").value.toString() +
                        snapshot.child("lastName2").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}