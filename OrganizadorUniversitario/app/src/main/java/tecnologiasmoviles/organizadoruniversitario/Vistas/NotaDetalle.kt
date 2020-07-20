package tecnologiasmoviles.organizadoruniversitario.Vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nota_detalle.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.R


class NotaDetalle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_detalle)
        title = "Detalles"

        val nota = intent.getSerializableExtra("Nota") as Nota
        var notas_lista = arrayListOf<String>()
        var i = 1

/**
        for (nota in nota.notas){
            notas_lista.add("Nota "+i+": "+nota)
            i++
        }
        lista_notas.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,notas_lista)
 */
       curso.text = nota.refCurso.toString()
        //curso.text = 1.toString()
        //val promedio1= calcularPromedio(nota.notas)
        val promedio1= 5.0
         promedio.text = promedio1.toString()

    }
}

/**
 * Función para calcular el promedio de notas que estan en un arreglo
 */
public fun calcularPromedio(notas_original:Array<Double>):Double{
    val notas_prom = notas_original.copyOf()
    var promedio = 0.0
    for (nota in notas_prom){
        promedio = promedio + nota
    }
    return Math.round((promedio/notas_prom.size) * 10.0) / 10.0
}
