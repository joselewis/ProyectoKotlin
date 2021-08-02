package com.example.pruebateatro

class CapaDatos {
    data class EMPRESA(

        @SerializedName("CEDULAJURIDICA") var CEDULAJURIDICA: String,
        @SerializedName("DESPEDIDA") var DESPEDIDA: String,
        @SerializedName("DIRECCION") var DIRECCION: String,
        @SerializedName("DIRECCIONXY") var DIRECCIONXY: String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("IMAGENURL") var IMAGENURL: String,
        @SerializedName("IMAGENURLMOVIL") var IMAGENURLMOVIL: String,
        @SerializedName("IMPUESTOAPLICA") var IMPUESTOAPLICA: String,
        @SerializedName("NOMBRE") var NOMBRE: String,
        @SerializedName("PAGADO") var PAGADO: String,
        @SerializedName("SALUDO") var SALUDO: String,
        @SerializedName("TELEFONO1") var TELEFONO1: String,
        @SerializedName("TELEFONO2") var TELEFONO2: String,
        @SerializedName("URLESCENARIO") var URLESCENARIO: String
    )

    annotation class SerializedName(val value: String)

    data class EVENTO(

        @SerializedName("DESCRIPCION") var DESCRIPCION: String,
        @SerializedName("DETALLE") var DETALLE: String,
        @SerializedName("EMPRESA") var EMPRESA: String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("IMAGENURL") var IMAGENURL: String,
        @SerializedName("IMPUESTO") var IMPUESTO: Number,
        @SerializedName("NOMBRE") var NOMBRE: String
    )
    data class LISTA_PRECIOS(

        @SerializedName("CODIGO_ACCESO") var CODIGO_ACCESO:String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("NOMBRE") var NOMBRE:String,
        @SerializedName("NUMERO") var NUMERO:Number,
        @SerializedName("TOTAL") var TOTAL:Number
    )

    //clase padre donde se utiliza relaciones de composicion con las clases anteriores para obtener una unica clase con los
    //atributos necesarios que devuelve la peticion
    data class FECHA(

        @SerializedName("ANNO") var ANNO: Number,
        @SerializedName("CERRADO") var CERRADO: String,
        @SerializedName("DIA") var DIA: Number,
        @SerializedName("EMPRESA") var EMPRESA: EMPRESA,
        @SerializedName("EVENTO") var EVENTO: EVENTO,
        @SerializedName("HORAS_CADUCA") var HORAS_CADUCA: Number,
        @SerializedName("HORA_FIN") var HORA_FIN:String,
        @SerializedName("HORA_INICIA") var HORA_INICIA:String,
        @SerializedName("ID") var ID: Number,
        @SerializedName("LISTA_PRECIOS") var LISTA_PRECIOS: LISTA_PRECIOS,
        @SerializedName("MES") var MES: Number,
        @SerializedName("NUMERO_HORA_FIN") var NUMERO_HORA_FIN: Number,
        @SerializedName("NUMERO_HORA_INICIA") var NUMERO_HORA_INICIA: Number,
        @SerializedName("NUMERO_MINUTO_FIN") var NUMERO_MINUTO_FIN: Number,
        @SerializedName("NUMERO_MINUTO_INICIA") var NUMERO_MINUTO_INICIA: Number,
        @SerializedName("TERMINAL") var TERMINAL:String

    )
}