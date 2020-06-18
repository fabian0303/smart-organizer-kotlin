package tecnologiasmoviles.organizadoruniversitario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nota_detalle.*
import kotlinx.android.synthetic.main.item_nota.*


class NotaDetalle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_detalle)
        title = "Detalles"
        val nota = intent.getSerializableExtra("Nota") as Nota

        curso.text = nota.nombreCurso
        val promedio1= calcularPromedio(nota.notas)
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
