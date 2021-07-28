package com.example.pruebateatro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.calendario_layout.*

class calendario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendario_layout)

        comprarTiquetes()
        escogerFecha()
    }

    private fun comprarTiquetes(){
        buttonComprarEntradasNuevas.setOnClickListener {
            val intent = Intent(this, compraTiquetes::class.java)
            startActivity(intent)
        }
    }

    private fun escogerFecha(){

        calendarTeatro.setOnDateChangeListener { view, anno, mes, dia ->
            val fechaEscogida = "La fecha seleccionada es " +  dia + "/" + (mes + 1) + "/" + anno
            Toast.makeText(this, fechaEscogida, Toast.LENGTH_SHORT).show()
        }
    }
}