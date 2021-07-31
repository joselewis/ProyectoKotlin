package com.example.pruebateatro

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.registro_usuario.*

class RegistrarUsuario : AppCompatActivity() {

    private lateinit var txtNombre:EditText
    private lateinit var txtApellido1:EditText
    private lateinit var txtApellido2:EditText
    private lateinit var txtCorreo:EditText
    private lateinit var txtContrasenna:EditText

    private lateinit var progressBar: ProgressBar

    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuario)

        txtNombre = findViewById(R.id.txtRegistroNombre)
        txtApellido1 = findViewById(R.id.txtRegistroApellido)
        txtApellido2 = findViewById(R.id.txtRegistroApellido2)
        txtCorreo = findViewById(R.id.txtRegistroCorreo4)
        txtContrasenna = findViewById(R.id.txtRegistroContrasenna)

        progressBar = findViewById(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")

    }

    fun registrar(view: View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name: String = txtNombre.text.toString()
        val lastName: String = txtApellido1.text.toString()
        val lastName2: String = txtApellido2.text.toString()
        val email: String = txtCorreo.text.toString()
        val password: String = txtContrasenna.text.toString()

        if(!TextUtils.isEmpty(name) &&
            !TextUtils.isEmpty(lastName) &&
            !TextUtils.isEmpty(lastName2) &&
            !TextUtils.isEmpty(email) &&
            !TextUtils.isEmpty(password)
        ){
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isComplete){
                        val user:FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userDB = dbReference.child(user?.uid!!)

                        userDB.child("Name").setValue(name)
                        userDB.child("lastName").setValue(lastName)
                        userDB.child("lastName2").setValue(lastName2)
                        action()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }

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