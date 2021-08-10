package com.example.pruebateatro

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.compra_tiquetes.*

class compraTiquetes : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null

    lateinit var cantidadaAsientos: TextView
    lateinit var numeroTarjeta: TextView

    val valorVIP = 5000
    val valorPrefe = 1500
    val valorNormal = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compra_tiquetes)

        var cajaVIP = findViewById(R.id.txtCantidadAsientosVIP) as EditText
        var cajaPrefe = findViewById(R.id.txtCantidadAsientosPrefe) as EditText
        var cajaNormal = findViewById(R.id.txtCantidadAsientosNormal) as EditText

        buttonPago.setOnClickListener {
            val tarjeta = txtNumeroTarjeta.text.toString()
            val monto = textViewMontoTotal.text.toString()

            createMetodoPago(tarjeta, monto)
        }

        buttonCalcularPago.setOnClickListener {
            var resultadoVIP =  (cajaVIP.text.toString().toInt() * valorVIP).toString()
            var resultadoPrefe =  (cajaPrefe.text.toString().toInt() * valorPrefe).toString()
            var resultadoNormal =  (cajaNormal.text.toString().toInt() * valorNormal).toString()
            textViewMontoTotal.text = "El monto a pagar es de: " + "₡" +(resultadoVIP.toInt() + resultadoPrefe.toInt() + resultadoNormal.toInt()).toString()
        }
    }

    private fun createMetodoPago(tarjeta: String, monto:String){
        val db = FirebaseFirestore.getInstance()
        val pago: MutableMap<String, Any> = HashMap()

        pago["Tarjeta"] = tarjeta
        pago["Monto"] = monto

        db.collection("pago")
            .add(pago)
            .addOnSuccessListener {
                verTiquete()
                Toast.makeText(this, "Método de pago guardado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error al guardar el método de pago", Toast.LENGTH_SHORT).show()
            }
    }

    private fun verTiquete(){
        val intent = Intent(this, tiquetes::class.java)
        startActivity(intent)
    }
}

