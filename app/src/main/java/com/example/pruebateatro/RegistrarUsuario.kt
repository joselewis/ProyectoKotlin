package com.example.pruebateatro

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.recyclerv_item_calendar.*
import kotlinx.android.synthetic.main.registro_usuario.*

class RegistrarUsuario : AppCompatActivity() {

    //Variables de cada caja de texto u otro componente usado en la pantalla de registro
    private lateinit var txtNombre:EditText
    private lateinit var txtApellido1:EditText
    private lateinit var txtApellido2:EditText
    private lateinit var txtCorreo:EditText
    private lateinit var txtContrasenna:EditText
    private lateinit var txtNombreUsuario:EditText

    //Variable para la barra de carga
    private lateinit var progressBar: ProgressBar

    //Variables para generar la conexión con la base de datos de Firebase
    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase

    //Variable de autenticación de Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuario)

////////////////////////////////////////MAPA Y CAMARA///////////////////////////////////////////////
        //Log.d("crudo", RegistrarUsuario.SharedApp.prefs.name)

        //Variables creadas para ligar las cajas de texto y almacenar lo escrito en ellas
        txtNombre = findViewById(R.id.txtRegistroNombre)
        txtApellido1 = findViewById(R.id.txtRegistroApellido)
        txtApellido2 = findViewById(R.id.txtRegistroApellido2)
        txtCorreo = findViewById(R.id.txtRegistroCorreo4)
        txtContrasenna = findViewById(R.id.txtRegistroContrasenna)
        txtNombreUsuario = findViewById(R.id.txtRegistroNombreUsuario)

        //Variable para utilizar más adelante la barra de cargar
        progressBar = findViewById(R.id.progressBar)

        //Variables para generar la conexión con la base de datos de Firebase
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")

    }

    //Método para ejecutar el botón registrar
    //(Aprete la tecla "ctrl izquierdo + espacio" sobre "registrar", eso la lleva donde esta el "ID" del botón)
    //Es una forma diferente de ligar el botón con el evento que tiene que hacer
    fun registrar(view: View){
        createNewAccount()
    }

    //Método que crea la nueva cuenta
    private fun createNewAccount(){

        //Variables que ligan las cajas con una variable nueva para poderlas manipular con la base de datos
        val name: String = txtNombre.text.toString()
        val lastName: String = txtApellido1.text.toString()
        val lastName2: String = txtApellido2.text.toString()
        val email: String = txtCorreo.text.toString()
        val password: String = txtContrasenna.text.toString()
        val nombreUsuario: String = txtNombreUsuario.text.toString()

        //Comprobación para ver que las cajas de texto no estén vacías
        if(!TextUtils.isEmpty(name) &&
            !TextUtils.isEmpty(lastName) &&
            !TextUtils.isEmpty(lastName2) &&
            !TextUtils.isEmpty(email) &&
            !TextUtils.isEmpty(password) &&
            !TextUtils.isEmpty(nombreUsuario)
        ){
            //La barra de progreso comienza a funcionar cuando se dé click en el botón registrar
            progressBar.visibility = View.VISIBLE

            //Autenticación que crea la nueva cuenta
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isComplete){
                        val user:FirebaseUser? = auth.currentUser

                        //Llamada al método "verifyEmail" (Está en la línea 123)
                        verifyEmail(user)

                        val userDB = dbReference.child(user?.uid!!)

                        userDB.child("Name").setValue(name)
                        userDB.child("lastName").setValue(lastName)
                        userDB.child("lastName2").setValue(lastName2)
                        userDB.child("email").setValue(email)
                        userDB.child("usuario").setValue(nombreUsuario)

                        /*Llamada al método que se ejecuta si todas las cajas de texto fueron llenadas
                        para posteriormente guardar la informacion y pasar al MainActivity (Login)
                        para que el usuario inicie sesión con la cuenta recién creada*/
                        action()
                    }
                }
        }
    }

    /*Método que envía al usuario al login sí salió bien el registro*/
    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    //Método que le envía un correo de verificaión al usuario para activar la cuenta
    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task ->
                if(task.isComplete){
                    Toast.makeText(this, "Correo enviado" , Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error al enviar el correo" , Toast.LENGTH_LONG).show()
                }
            }
    }
}