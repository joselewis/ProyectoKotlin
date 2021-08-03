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

//Método que permite conectar el login con el acceso a la PaginaPrincipal
enum class ProviderType{
    BASIC
}

class PaginaPrincipal : AppCompatActivity() {

    //Variables para realizar la conexión con la base de datos
    private lateinit var auth: FirebaseAuth
    var  databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    //Crea un ShareApp para connectar la aplicaión con la API
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

    //Método para conectar el Adapter con el RecyclerView
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

        //Variables para la conexión con Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("User")

        //Llamda al método que carga el perfil del usuario
        loadProfile()

        //Botón que llama la lista de obras activas en el mes 
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

    //Liga la obra con la fecha
    interface APIService {
        @POST("Calendario")
        @FormUrlEncoded
        fun registrationPost(
            @Field("op") op: String,
            @Field("mes") mes: String,
            @Field("anno") anno: String
        ): Call<MutableList<CapaDatos.FECHA>>
    }

    //Conecta la API para obtener la información de cada obra
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://taquillas.sistemcr.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Método que carga el nombre, correo y nombre del usuario en el apartado del perfil
    private fun loadProfile(){

        //Se abre la conexión con Firebase
        val user = auth.currentUser
        val userReference = databaseReference?.child(user?.uid!!)

        userReference?.addValueEventListener(object: ValueEventListener{

            //Saca de la base de datos el Nombre, Usuario y Correo para presentarlo en los TextView
            override fun onDataChange(snapshot: DataSnapshot) {
                textViewNombre.text = "Nombre " + snapshot.child("Name").value.toString() + " " +
                                      snapshot.child("lastName").value.toString() + " " +
                                      snapshot.child("lastName2").value.toString()
                textViewCorreo.text = "Correo " + snapshot.child("email").value.toString()
                textViewNombreUsuario.text = "Usuario " + snapshot.child("usuario").value.toString()
            }

            /*UNA HABLADA CUALQUIERA QUE MANDE AL USUARIO AL CARAJO XD*/
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}