package com.example.pruebateatro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adaperPrecios { /*: RecyclerView.Adapter<adaperPrecios.ViewHolder>() {

    var precios:MutableList<CapaDatos.LISTA_PRECIOS> = ArrayList()

    lateinit var context: Context

    fun RecyclerAdapter(precios: MutableList<CapaDatos.LISTA_PRECIOS>, context: Context){
        this. precios = precios
        this.context = context

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = precios.get(position)
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout., parent, false))
    }
    override fun getItemCount(): Int {
        return precios.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nombre = view.findViewById(R.id.nombre) as TextView

        val descripcion = view.findViewById(R.id.descripcion) as TextView
        val horadeinicio = view.findViewById(R.id.horadeinicio) as TextView
        val teatro = view.findViewById(R.id.teatro) as TextView
        val horafinaliza = view.findViewById(R.id.horafinaliza) as TextView
        val duracion = view.findViewById(R.id.duracion) as TextView
        val foto = view.findViewById(R.id.foto) as ImageView
        val dia = view.findViewById(R.id.dia) as TextView
        val botoncompra = view.findViewById(R.id.btncomprar) as Button


        fun bind(precios: CapaDatos.LISTA_PRECIOS, context: Context){

            nombre.text = fecha.EVENTO.NOMBRE
            descripcion.text = fecha.EVENTO.DESCRIPCION
            horadeinicio.text= "Empieza: " +  fecha.NUMERO_HORA_INICIA.toString() + ":" + fecha.NUMERO_MINUTO_INICIA.toString()
            teatro.text= fecha.EMPRESA.NOMBRE;
            horafinaliza.text= "Termina: " +  fecha.NUMERO_HORA_FIN .toString() + ":" + fecha.NUMERO_MINUTO_FIN.toString()
            dia.text = fecha.DIA.toString()
            foto.loadUrl("http://taquillas.sistemcr.com/Imageneseventos/" + fecha.EVENTO.ID.toString() +".jpg")

            botoncompra.setOnClickListener {

                val intent = Intent(context, calendario::class.java)
                intent.putExtra("Obra", fecha.EVENTO.ID.toString())
                intent.putExtra("Precio", fecha.LISTA_PRECIOS.ID.toString())
                intent.putExtra("Empresa", fecha.EMPRESA.ID.toString())
                intent.putExtra("Fecha", fecha.ID.toString())

                context.startActivity(intent)
            }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }*/
}