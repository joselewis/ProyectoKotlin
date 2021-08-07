package com.example.pruebateatro

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class adaperCamaraUbi //: RecyclerView.Adapter<adaperCamaraUbi.ViewHolder>()
 {
/*
    var fotos:MutableList<MainActivity.foto> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(foto: MutableList<MainActivity.foto>, context: Context){
        this.fotos = foto
        this.context = context

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = fotos.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_fotos_tomadas, parent, false))
    }

    override fun getItemCount(): Int {
        return fotos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val longitud = view.findViewById(R.id.lon) as TextView

        val latitud = view.findViewById(R.id.lat) as TextView

        val fotot = view.findViewById(R.id.foto) as ImageView
        val botonelimina = view.findViewById(R.id.btnelimina) as Button

        fun bind(foto: MainActivity.foto, context: Context){
            longitud.text = foto.ejey
            latitud.text =  foto.ejex

            longitud.setOnClickListener{
                val intent = Intent(context,MapaActivity::class.java);
                intent.putExtra("ln",foto.ejey);
                intent.putExtra("lg",foto.ejex);
                context.
                startActivity(intent);
            }
            fotot.setImageURI(
            Uri.parse(foto.imagencruda))


            botonelimina.setOnClickListener(){

                var dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                MainActivity.SharedApp.fotos.remove(foto)
                                Toast.makeText(context, " eliminado", Toast.LENGTH_SHORT).show()

                                val gson = Gson()
                                val listaser = gson.toJson(MainActivity.SharedApp.fotos)
                                // sharedPreferences.edit().putString(USER_PROFILE, serializedUser).apply()
                                MainActivity.SharedApp.prefs.name = listaser
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {

                            }
                        }
                    }

                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setMessage("Desea eliminar esta foto ?").setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show()
            }

        }
    }
 */
}