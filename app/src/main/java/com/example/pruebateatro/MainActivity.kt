package com.example.pruebateatro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ingresar()

        buttonIR_Registrarse.setOnClickListener {
            var intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }
    }

    private fun ingresar(){
        buttonIngresar.setOnClickListener {
            if (txtCorreoLogin.text.isNotEmpty() && txtContrasennaLogin.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    txtCorreoLogin.text.toString(),
                    txtContrasennaLogin.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        ingresoExitoso(it.result?.user?.email?: "", ProviderType.BASIC )
                    } else{
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun ingresoExitoso(email: String, provider: ProviderType){
        val intent = Intent(this, PaginaPrincipal::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
    }

}