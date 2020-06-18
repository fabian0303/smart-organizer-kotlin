package tecnologiasmoviles.organizadoruniversitario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_nota.view.*

class NotasAdapter(private val mContext: Context, private val listaNotas: List<Nota>):ArrayAdapter<Nota>(mContext,0,listaNotas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_nota,parent,false)

        val notas = listaNotas[position]

        layout.nombreCurso.text = notas.nombreCurso

        val promedio= calcularPromedio(notas.notas)

        layout.nota.text = promedio.toString()

        return layout
    }

    /**
     * Función para calcular el promedio de notas que estan en un arreglo
     */
    private fun calcularPromedio(notas_original:Array<Double>):Double{
        val notas_prom = notas_original.copyOf()
        var promedio = 0.0
        for (nota in notas_prom){
            promedio = promedio + nota
        }
        return Math.round((promedio/notas_prom.size) * 10.0) / 10.0
    }
}