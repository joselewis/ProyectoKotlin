package com.example.pruebateatro

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class PaginaPrincipal : AppCompatActivity() {


    //Variables para realizar la conexión con la base de datos
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler)

        //Variables para la conexión con Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("User")

        //Llamda al método que carga el perfil del usuario
        loadProfile()
        cargarObras()
    }

    private fun loadProfile() {

        //Se abre la conexión con Firebase
        val user = auth.currentUser
        val userReference = databaseReference?.child(user?.uid!!)

        userReference?.addValueEventListener(object : ValueEventListener {

            //Saca de la base de datos el Nombre, Usuario y Correo para presentarlo en los TextView
            override fun onDataChange(snapshot: DataSnapshot) {
                textViewNombre.text = "Nombre: " + snapshot.child("Name").value.toString() + " " +
                        snapshot.child("lastName").value.toString() + " " +
                        snapshot.child("lastName2").value.toString()
                textViewCorreo.text = "Correo: " + snapshot.child("email").value.toString()
                textViewNombreUsuario.text =
                    "Usuario: " + snapshot.child("usuario").value.toString()
            }

            /*UNA HABLADA CUALQUIERA QUE MANDE AL USUARIO AL CARAJO XD*/
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun cargarObras() {
        buttonVerObras.setOnClickListener {
            val intent = Intent(this, calendario::class.java)
            startActivity(intent)
        }
    }
}
