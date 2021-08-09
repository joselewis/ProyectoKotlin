package com.example.pruebateatro

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.butacas_activity.*
import kotlinx.android.synthetic.main.calendario_layout.*

class Butacas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.butacas_activity)

        compra()

        var animacionButaca = false

        imageViewButacaVacia1.setOnClickListener {
           if(imageViewButacaVacia1.isAnimating == false){
               animacionButaca = animarButaca(imageViewButacaVacia1, R.raw.success, animacionButaca)
               val valorButaca = 1
               textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
           }
        }
        imageViewButacaVacia2.setOnClickListener {
            if(imageViewButacaVacia2.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia2, R.raw.success, animacionButaca)
                val valorButaca = 2
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia3.setOnClickListener {
            if(imageViewButacaVacia3.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia3, R.raw.success, animacionButaca)
                val valorButaca = 3
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia4.setOnClickListener {
            if(imageViewButacaVacia4.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia4, R.raw.success, animacionButaca)
                val valorButaca = 4
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia5.setOnClickListener {
            if(imageViewButacaVacia5.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia5, R.raw.success, animacionButaca)
                val valorButaca = 5
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia6.setOnClickListener {
            if(imageViewButacaVacia6.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia6, R.raw.success, animacionButaca)
                val valorButaca = 6
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia7.setOnClickListener {
            if(imageViewButacaVacia7.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia7, R.raw.success, animacionButaca)
                val valorButaca = 7
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia8.setOnClickListener {
            if(imageViewButacaVacia8.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia8, R.raw.success, animacionButaca)
                val valorButaca = 8
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia9.setOnClickListener {
            if(imageViewButacaVacia9.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia9, R.raw.success, animacionButaca)
                val valorButaca = 9
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia10.setOnClickListener {
            if(imageViewButacaVacia10.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia10, R.raw.success, animacionButaca)
                val valorButaca = 10
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia11.setOnClickListener {
            if(imageViewButacaVacia11.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia11, R.raw.success, animacionButaca)
                val valorButaca = 11
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia12.setOnClickListener {
            if(imageViewButacaVacia12.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia12, R.raw.success, animacionButaca)
                val valorButaca = 12
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia13.setOnClickListener {
            if(imageViewButacaVacia13.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia13, R.raw.success, animacionButaca)
                val valorButaca = 13
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia14.setOnClickListener {
            if(imageViewButacaVacia14.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia14, R.raw.success, animacionButaca)
                val valorButaca = 14
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia15.setOnClickListener {
            if(imageViewButacaVacia15.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia15, R.raw.success, animacionButaca)
                val valorButaca = 15
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
        imageViewButacaVacia16.setOnClickListener {
            if(imageViewButacaVacia16.isAnimating == false){
                animacionButaca = animarButaca(imageViewButacaVacia16, R.raw.success, animacionButaca)
                val valorButaca = 16
                textViewButacasEscogidas.text = "Butaca: " + valorButaca.toString()
            }
        }
    }

    private fun animarButaca(imageView: LottieAnimationView, animacion: Int, estado:Boolean) : Boolean{
        if(!estado){
            imageView.setAnimation(animacion)
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.drawable.butaca)
        }
        return !estado
    }

    private fun compra(){
        buttonComprarEntradas.setOnClickListener {
            val intent = Intent(this, compraTiquetes::class.java)
            startActivity(intent)
        }
    }
}