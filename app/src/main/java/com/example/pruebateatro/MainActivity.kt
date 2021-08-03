package com.example.pruebateatro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Variables para autenticar el logueo del usuario
    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText

    //Variable de Firebase para generar una autenticación
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variables ligadas al "ID" de las cajas de texto
        txtUser = findViewById(R.id.txtCorreoLogin)
        txtPassword = findViewById(R.id.txtContrasennaLogin)

        //Instancia con la autenticación de Firebase
        auth =  FirebaseAuth.getInstance()

    }

    //Método que ejecuta el botón "Registrarse"
    fun buttonIR_Registrarse(view:View){
        startActivity(Intent(this, RegistrarUsuario::class.java))
    }

    //Método que ejecuta el botón "Ingresar"
    fun Ingresar(view:View){
        loginUser()
    }

    //Función que comprueba que el correo y contraseña sean válidos
    private fun loginUser(){

        //Variables asigandas a las cajas de texto con el correo y contraseña escritos
        val user:String = txtUser.text.toString()
        val password:String = txtPassword.text.toString()

        //Valida que el usuario y contraseña existan
        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){

                        //Llamada al método "action" para ejecutar la comprobación
                        action()
                    }else{
                        Toast.makeText(this, "Correo o contraseña incorrecta" , Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    //Método que al ser el correo y contraseña correcto, se ejecuta para enviar al usuario a la pantalla principal
    private fun action(){
        startActivity(Intent(this, PaginaPrincipal::class.java))
    }
}