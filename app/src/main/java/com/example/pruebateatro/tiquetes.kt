package com.example.pruebateatro

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.recycler.textViewCorreo
import kotlinx.android.synthetic.main.tiquete_activity.*

class tiquetes : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiquete_activity)

        //Variables para la conexión con Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("User")

        //Llamda al método que carga el perfil del usuario
        loadProfile()
        cargarPago()
        volverPerfil()
    }

    private fun loadProfile() {

        //Se abre la conexión con Firebase
        val user = auth.currentUser
        val userReference = databaseReference?.child(user?.uid!!)

        userReference?.addValueEventListener(object : ValueEventListener {

            //Saca de la base de datos el Nombre, Usuario y Correo para presentarlo en los TextView
            override fun onDataChange(snapshot: DataSnapshot) {
                textViewPagoNombre.text = "Nombre: " + snapshot.child("Name").value.toString() + " " +
                        snapshot.child("lastName").value.toString() + " " +
                        snapshot.child("lastName2").value.toString()
                textViewPagoCorreo.text = "Correo: " + snapshot.child("email").value.toString()
            }

            /*UNA HABLADA CUALQUIERA QUE MANDE AL USUARIO AL CARAJO XD*/
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun cargarPago(){

        val db = FirebaseFirestore.getInstance()

        db.collection("pago")
            .get()
            .addOnCompleteListener {

                val monto: StringBuffer = StringBuffer()
                val tarjeta: StringBuffer = StringBuffer()

                if(it.isSuccessful){
                    for (document in it.result!!){
                        monto.append(document.data.getValue("Monto")).append(" ")
                        tarjeta.append(document.data.getValue("Tarjeta")).append(" ")
                    }
                }
                 textViewPagoMontoTotal.setText(monto)
                 textViewPagoNumeroTarjeta.setText("Número de tarjeta: " + tarjeta)
            }
    }

    private fun volverPerfil(){

        buttonVolverPerfil.setOnClickListener {
            val intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        }
    }
}